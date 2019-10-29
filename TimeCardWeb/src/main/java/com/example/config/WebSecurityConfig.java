package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.example.auth.CsrfTokenFilter;
import com.example.auth.SimpleAccessDeniedHandler;
import com.example.auth.SimpleAuthenticationEntryPoint;
import com.example.auth.SimpleAuthenticationFailureHandler;
import com.example.auth.SimpleAuthenticationSuccessHandler;





@EnableWebSecurity
@ImportResource("classpath:META-INF/spring/applicationContext.xml")
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{


	private /*final*/ AuthenticationManagerBuilder authenticationManagerBuilder;

	@Autowired
	@Qualifier("userAccountService")
	private /*final*/ UserDetailsService userDetailsService;

	public void WebSecurityConfiguration(AuthenticationManagerBuilder _authenticationManagerBuilder) {
		    this.authenticationManagerBuilder = _authenticationManagerBuilder;
		  }


    private CsrfTokenRepository getCsrfTokenRepository() {
        // Javascriptから取得できるようにHttponlyをfalseにする/https以外(http)でも有効に設定する。
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        // pathを/にすることでクライアントから読み取れるようにする
        tokenRepository.setCookiePath("/");
        return tokenRepository;
    }

    @Autowired
    PasswordEncoder passwordEncoder;


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
		//http.authorizeRequests().anyRequest().authenticated();


		http.addFilterAfter(new CsrfTokenFilter(), CsrfFilter.class);
         //.csrf().disable(); // disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!

		// EXCEPTION
		http
        .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())
        .and()
        // LOGIN
        .formLogin().loginPage("/")
            .loginProcessingUrl("/api/login").permitAll()
                .usernameParameter("loginId")
                .passwordParameter("password")
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
        .and()
        // LOGOUT
        .logout()
            .logoutUrl("/api/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler())
            //.addLogoutHandler(new CookieClearingLogoutHandler())
        .and()
         // CSRF
        .csrf()
            //.disable()
            .ignoringAntMatchers("/api/login")
            .csrfTokenRepository(new CookieCsrfTokenRepository());
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Qualifier("userAccountService") UserDetailsService userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        auth.eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }

    AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }

    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

    LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
}
