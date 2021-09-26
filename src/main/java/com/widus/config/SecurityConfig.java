package com.widus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

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
                   .antMatchers("/board/board_write.do").authenticated()
                   	.anyRequest().permitAll()
               .and()
                   .logout()
                       .logoutSuccessUrl("/")
               .and()
	               .formLogin()
	               		.permitAll()
	               		.loginPage("/loginPage")
               .and()
                   .oauth2Login()
                   	   .successHandler(successHandler())
                       .userInfoEndpoint()
                           .userService(customOAuth2UserService);
   }
}