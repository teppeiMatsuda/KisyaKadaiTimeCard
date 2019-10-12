package com.example.config;

import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@ImportResource("classpath:META-INF/spring/applicationContext.xml")
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 1.データソースDB設定などはこちら → KishaKadaiTimeCard-env.xml
		// 2.ドメイン(MapperBean)などの設定はこちら → KishaKadaiTimeCard-infra.xml
		// 3.セキュリティ関連はこちら → spring-security.xml
		// 4.アプリケーションコンテキスト(上記1～3やproperty等を読み込み) → applicationContext.xml
	}
}
