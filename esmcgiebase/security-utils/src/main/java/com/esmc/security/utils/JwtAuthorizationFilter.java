package com.esmc.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
            if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.AUTH_PREFIX)) {
                String jwt = authorizationToken.substring(JWTUtil.AUTH_PREFIX.length());
                Algorithm algo = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jWTVerifier = JWT.require(algo).build();
                DecodedJWT decodedJWT = jWTVerifier.verify(jwt);

                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (String r : roles) {
                    authorities.add(new SimpleGrantedAuthority(r));
                }
                JwtAuthenticationToken upat = new JwtAuthenticationToken(username, null, authorities, jwt);

                SecurityContextHolder.getContext().setAuthentication(upat);

                filterChain.doFilter(request, response);
            } else {

                filterChain.doFilter(request, response);
            }
        }
    }
}
