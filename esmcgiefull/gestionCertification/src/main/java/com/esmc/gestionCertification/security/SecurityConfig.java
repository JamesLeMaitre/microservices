/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.esmc.gestionCertification.security;


import com.esmc.security.utils.BaseSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author user
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends BaseSecurityConfig {
    
}
