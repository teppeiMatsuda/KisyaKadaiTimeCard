package com.example.config;

import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@ImportResource("classpath:META-INF/spring/applicationContext.xml")
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1.データソースDB設定などはこちら → KishaKadaiTimeCard-env.xml
		// 2.ドメイン(MapperBean)などの設定はこちら → KishaKadaiTimeCard-infra.xml
		// 3.セキュリティ関連はこちら → spring-security.xml
		// 4.アプリケーションコンテキスト(上記1～3やproperty等を読み込み) → applicationContext.xml

        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring security
        .and()
            .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/user/**").permitAll() // allow every URI, that begins with '/api/user/'
                .antMatchers("/api/secured").authenticated()
                //.anyRequest().authenticated() // protect all other requests
        .and()
            .csrf().disable(); // disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!
	}
}
