package com.widus.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.widus.auth.CustomOAuth2UserService;
import com.widus.dto.user.UserRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final CustomOAuth2UserService customOAuth2UserService;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   
       http
               .csrf().disable()
               .headers().frameOptions().disable() 
               .and()
                   .authorizeRequests()
//	                   .antMatchers("/", "/index", "/login", "/css/**", "/images/**",
//	                           "/js/**", "/h2-console/**", "/fonts/**").permitAll()
//	                   .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//	                   .anyRequest().authenticated()
                   	.anyRequest().permitAll()
               .and()
                   .logout()
                       .logoutSuccessUrl("/")
               .and()
                   .oauth2Login()
                       .userInfoEndpoint()
                           .userService(customOAuth2UserService);
   }
}