package com.skillstorm.demo.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/logout").permitAll()
			  .anyRequest().authenticated()
			  .and()
			  .csrf().disable()
			  .oauth2Login()
			  .and()
			  .logout(logout -> logout.permitAll()

	                    .logoutSuccessHandler((request, response, authentication) -> {

	                        response.setStatus(HttpServletResponse.SC_OK);

	                    }));
			  
		
		// Redirect URI
		// http://localhost:8080/login/oauth2/code/google
		
		// Configure cors at the Security level
		http.cors().configurationSource(request -> {
			CorsConfiguration corsConfig = new CorsConfiguration();
			corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
			corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
			corsConfig.setAllowCredentials(true); // Allows cookies
			corsConfig.setMaxAge(3600L); // For an hour, cache the preflight cors request (OPTIONS)
			
			// Sets this cors config to be on ALL requests
			// If it was /auth/**, it would only apply it to any request starting with /auth
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", corsConfig);
			
			return corsConfig;
		});
		
		return http.build();
	}
	
	
}