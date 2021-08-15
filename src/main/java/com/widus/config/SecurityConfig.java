package com.widus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.widus.auth.CustomLoginSuccessHandler;
import com.widus.auth.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final CustomOAuth2UserService customOAuth2UserService;
   
   @Bean
   public AuthenticationSuccessHandler successHandler() {
       return new CustomLoginSuccessHandler("/");
   }

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
	               .formLogin()
		               .loginPage("/login")
		               .successHandler(successHandler())
		               .permitAll()
               .and()
                   .oauth2Login()
                       .userInfoEndpoint()
                           .userService(customOAuth2UserService);
   }
}