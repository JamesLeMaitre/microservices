package com.esmc.gestionte.security;

import com.esmc.security.utils.BaseSecurityConfig;
import com.esmc.security.utils.JwtAuthenticationFilter;
import com.esmc.security.utils.JwtAuthorizationFilter;
import feign.Request;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

}
