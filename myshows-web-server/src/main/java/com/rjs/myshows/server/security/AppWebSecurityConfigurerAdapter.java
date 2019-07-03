package com.rjs.myshows.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rjs.myshows.server.config.AppProperties;

@Configuration
@EnableWebSecurity
public class AppWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	private AppProperties appProperties;
	private AppAuthenticationEntryPoint authenticationEntryPoint;

	public AppWebSecurityConfigurerAdapter(AppProperties appProperties,
										   AppAuthenticationEntryPoint authenticationEntryPoint) {
		this.appProperties = appProperties;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication()
			.withUser(appProperties.getAuthName())
			.password(passwordEncoder().encode(appProperties.getAuthPass()))
			.authorities("ROLE_USER", "ROLE_ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors().disable()
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.authenticationEntryPoint(authenticationEntryPoint);
	}
}
