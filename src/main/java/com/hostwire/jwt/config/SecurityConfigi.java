package com.hostwire.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigi {

	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin=User.withUsername("Srikanth")
//				.password(encoder.encode("sri"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user=User.withUsername("RajTej")
//				.password(encoder.encode("sri1"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin, user);
		return new UserInfiUserDetailsService();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/products/welcome","/products/n").permitAll()
		.and()
		.authorizeHttpRequests().requestMatchers("/products/**").authenticated()
		.and().formLogin()
		.and().build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{	
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
}