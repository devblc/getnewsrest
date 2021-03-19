package com.bluet.bring.getnewsrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.bluet.bring.getnewsrest.auth.JwtRequestFilter;
import com.bluet.bring.getnewsrest.auth.service.UserAuthenticationService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailService);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth/sign-in").permitAll()
		//.antMatchers(HttpMethod.POST, "/auth/").permitAll()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
        .and().sessionManagement()        
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		//.antMatchers("/admin").hasRole("ADMIN")
        //.antMatchers("/user").hasAnyRole("ADMIN", "USER")
		//.and().formLogin()

	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(getPasswordEncoder());
	    return authProvider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
    // To enable CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	
        

        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));  //set access from all domains
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        //configuration.setAllowCredentials(true);
        //configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
       
        return source;
    }
	
	
	/*
    // To enable CORS
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
        //allowList.clear();
        //allowList = Arrays.asList("*");    	
        config.setAllowedOrigins(Arrays.asList("*"));  //set access from all domains

        //allowList.clear();
        //allowList = Arrays.asList("Authorization", "Cache-Control", "Content-Type");
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		
        //allowList.clear();
        //allowList = Arrays.asList("GET", "POST", "PUT", "DELETE");    	
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		
        source.registerCorsConfiguration("/**", config);
		CorsFilter bean = new CorsFilter(source);
		
		return bean;
		
	}
	*/
	


}
