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
@ImportResource("classpath:META-INF/spring/spring-security.xml")
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{


	@Qualifier("passwordEncoder")
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/", "/login", "login-error").permitAll()
//				.antMatchers("/**").hasRole("USER")
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.loginProcessingUrl("/authenticate")
//				.usernameParameter("loginId")
//				.passwordParameter("password")
//				.defaultSuccessUrl("/")
//				.failureUrl("/login-error");
//
//		http
//			.authorizeRequests()
//				.antMatchers("/registration", "/register","/result").permitAll();
	}


	@Autowired
	public void configureGrobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("root").password("password").roles("USER");
	}
}
