//package com.Bitts.configration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.DefaultSecurityFilterChain;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.Bitts.security.CustomUserDetailService;
//import com.Bitts.security.JWTAuthenticationEntryPoint;
//import com.Bitts.security.JWTAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//public class securityConfig {
//	@Autowired
//	private CustomUserDetailService customUserDetailService; 
//	@Autowired
//	private JWTAuthenticationEntryPoint authenticationEntryPoint;
//	@Autowired
//	private JWTAuthenticationFilter authenticationFilter;
//	
//	
//	@Bean
//	public  SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
//		http
//		.csrf()
//		.disable()
//		.authorizeHttpRequests()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		;
//		http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		
//		
//		DefaultSecurityFilterChain build=http.build();
//		return build;
//	}
////		
//	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passEnoder());
//	}
//	@Bean
//	public PasswordEncoder passEnoder() {
//
//		return new BCryptPasswordEncoder();
//	}
////	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
////	public AuthenticationManager authenticationManagerBean()throws Exception{
////		return super.authenticationManagerBean();
////	}
//	
//}
