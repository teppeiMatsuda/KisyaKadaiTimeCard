package com.example.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.example.auth.CsrfTokenFilter;
import com.example.auth.JsonLoginIDPasswordAuthenticationFilter;

@EnableWebSecurity
@ImportResource("classpath:META-INF/spring/applicationContext.xml")
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    private CsrfTokenRepository getCsrfTokenRepository() {
        // Javascriptから取得できるようにHttponlyをfalseにする/https以外(http)でも有効に設定する。
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        // pathを/にすることでクライアントから読み取れるようにする
        tokenRepository.setCookiePath("/");
        return tokenRepository;
    }

    // ↓ 多分もういらない。
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1.データソースDB設定などはこちら → KishaKadaiTimeCard-env.xml
		// 2.ドメイン(MapperBean)などの設定はこちら → KishaKadaiTimeCard-infra.xml
		// 3.セキュリティ関連はこちら → spring-security.xml
		// 4.アプリケーションコンテキスト(上記1～3やproperty等を読み込み) → applicationContext.xml

		// ヘッダにCSRFトークンを埋め込み
		// → Responseに対してセッションIDが付加されるがCSRFトークンが含まれない…
//        http.authorizeRequests()
//        	.and().csrf().csrfTokenRepository(getCsrfTokenRepository());
		http.authorizeRequests().anyRequest().authenticated();
		http
//		.httpBasic()
//        .and()
	      .csrf()
	        .csrfTokenRepository(getCsrfTokenRepository())
		.ignoringAntMatchers("");
		// ↑ CSRFトークン無くてもOKなパスを設定してるだけなので必要ないと思う…。
		http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class);
//        http.httpBasic().and().authorizeRequests()
//        .antMatchers("/").permitAll().anyRequest()
//        .authenticated().and().csrf()
//        .csrfTokenRepository(csrfTokenRepository()).and()
//        .addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class);
//        .and().csrf()
//        .csrfTokenRepository(getCsrfTokenRepository()).and()
//        .addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class);

//		http.csrf().disable();
//      http.authorizeRequests()
//    	.and().csrf().csrfTokenRepository(getCsrfTokenRepository());

//        http.csrf()
//        .csrfTokenRepository(getCsrfTokenRepository());

		// ログインパラメーターの設定
        JsonLoginIDPasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter =
            new JsonLoginIDPasswordAuthenticationFilter(authenticationManager());
        jsonUsernamePasswordAuthenticationFilter.setLoginIdParameter("loginId");
        jsonUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
        // ログイン後にリダイレクトのリダイレクトを抑制
        jsonUsernamePasswordAuthenticationFilter
            .setAuthenticationSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK));
        // ログイン失敗時のリダイレクト抑制
        jsonUsernamePasswordAuthenticationFilter
            .setAuthenticationFailureHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));

        // FormログインのFilterを置き換える
        //http.addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        // Spring Securityデフォルトでは、アクセス権限（ROLE）設定したページに未認証状態でアクセスすると403を返すので、
        // 401を返すように変更
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        // 今回は、403エラー時にHTTP Bodyを返さないように設定
        http.exceptionHandling().accessDeniedHandler((req, res, ex) -> {
        		System.out.println(res.getContentType() + "疎通確認");
        		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        		});

        // ログアウト
        http
            .logout()
            .logoutUrl("/api/logout")
            // ログアウト時のリダイレクト抑制
            .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
            .invalidateHttpSession(true);
//        http
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring security
//        .and()
//            .httpBasic()
//        .and()
//            .authorizeRequests()

                //.anyRequest().authenticated() // protect all other requests
        //http
        //.and()
            //.csrf().disable(); // disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!
	}
}
