package com.esmc.feign;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.esmc.security.utils.JWTUtil;
import com.esmc.security.utils.JwtAuthenticationToken;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FeignClientCronConfiguration {
        public String generateToken(){
            //HttpServletRequest request = null;
            String IsssueUrl = "https://www.esmcgie.com/";
            List<String> roles = Collections.emptyList();
            Algorithm algo = Algorithm.HMAC256(JWTUtil.SECRET);
            assert false;
            return JWT.create()
                    .withSubject("automate_user")
                    .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                    .withIssuer(IsssueUrl)
                    .withClaim("roles", new ArrayList<>(roles))
                    .sign(algo);
        }


    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return template -> template.header(JWTUtil.AUTH_HEADER, String.format("%s%s", JWTUtil.AUTH_PREFIX, generateToken()));
    }
}
