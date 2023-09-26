/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.esmc.gestionPayement.security;


import com.esmc.security.utils.BaseSecurityConfig;
import com.esmc.security.utils.JwtAuthenticationFilter;
import com.esmc.security.utils.JwtAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author user
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends BaseSecurityConfig {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.headers().frameOptions().disable();
//        http.authorizeRequests().antMatchers("/h2-console/**", "/refreshToken").permitAll();
        http.authorizeRequests().antMatchers("/api/in/transaction/request/cinetpay/webhook/notification").permitAll();
//        http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
