package com.esmc.feign;

import com.esmc.security.utils.JWTUtil;
import com.esmc.security.utils.JwtAuthenticationToken;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class FeignClientConfiguration {

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return template -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof JwtAuthenticationToken) {
                final JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;
                template.header(JWTUtil.AUTH_HEADER, String.format("%s%s", JWTUtil.AUTH_PREFIX, jwtAuthToken.getToken()));
            }
            ;
        };
    }
}
