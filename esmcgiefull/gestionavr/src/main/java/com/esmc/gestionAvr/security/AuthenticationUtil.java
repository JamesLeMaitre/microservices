package com.esmc.gestionAvr.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public final class AuthenticationUtil {

    //Ensures that this class cannot be instantiated
    private AuthenticationUtil() {
    }

    public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public static void configureAuthentication(String role) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "user1",
                role,
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}