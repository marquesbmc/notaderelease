package com.caixa.notaderelease.api.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.caixa.notaderelease.api.security.jwt.JwtAuthenticationEntryPoint;
import com.caixa.notaderelease.api.security.jwt.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/user/**").permitAll()       
                
                .antMatchers(HttpMethod.OPTIONS,"/api/ticket/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/ticket/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/ticket/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/ticket/**").permitAll()                  
                .antMatchers(HttpMethod.DELETE,"/api/ticket/**").permitAll() 
                
                .antMatchers(HttpMethod.OPTIONS,"/ticket-list/**").permitAll()
                .antMatchers(HttpMethod.POST,"/ticket-list/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/ticket-list/**").permitAll()
                .antMatchers(HttpMethod.GET,"/ticket-list/**").permitAll()    
                                
                .antMatchers(HttpMethod.OPTIONS,"/ticket-detail/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/ticket-detail/**").permitAll()
                .antMatchers(HttpMethod.GET,"/ticket-detail/**").permitAll()
                .antMatchers(HttpMethod.POST,"/ticket-detail/**").permitAll()
                
                .antMatchers(HttpMethod.OPTIONS,"/api/releasenotes/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/releasenotes/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/releasenotes/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/releasenotes/**").permitAll()
                
                .antMatchers(HttpMethod.OPTIONS,"/api/releaseNotes-list/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/releaseNotes-list/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/releaseNotes-list/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/releaseNotes-list/**").permitAll()
                
                .antMatchers(HttpMethod.OPTIONS,"/api/statusnotes/**").permitAll() 
                .antMatchers(HttpMethod.GET,"/api/statusnotes/**").permitAll()
                
                .antMatchers(HttpMethod.OPTIONS,"/api/systemnotes/**").permitAll() 
                .antMatchers(HttpMethod.GET,"/api/systemnotes/**").permitAll()
       
                
                
                //.antMatchers("/api/user/**").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
          
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
	}
}