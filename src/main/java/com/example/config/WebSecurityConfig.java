package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/login", "login-error").permitAll()
				.antMatchers("/**").hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/login").failureUrl("/login-error");
	}

	@Autowired
	public void configureGrobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("root").password("password").roles("USER");
	}
}
