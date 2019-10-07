package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@ImportResource("classpath:META-INF/spring/applicationContext.xml")
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{


	@Qualifier("passwordEncoder")
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1.データソースDB設定などはこちら → KishaKadaiTimeCard-env.xml
		// 2.ドメイン(MapperBean)などの設定はこちら → KishaKadaiTimeCard-infra.xml
		// 3.セキュリティ関連はこちら → spring-security.xml
		// 4.アプリケーションコンテキスト(上記1～3やproperty等を読み込み) → applicationContext.xml
	}

	@Autowired
	public void configureGrobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("root").password("password").roles("USER");
	}
}
