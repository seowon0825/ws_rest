package com.example.restServier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ //로그인을 안하고 들어가게 해줌.
		http.csrf(auth -> auth.disable());
		
		http.httpBasic(auth -> auth.disable());
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); //security를 적용하지 않은 것과 다름없어짐.
		
		return http.build();
	}
	
}
