package com.esmc.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;


public class CurrentUser {

    public CurrentUser() {
    }

    public String whichHasTheRequest(HttpServletRequest request) {

        String username = "";
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
           // Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            username = decodedJWT.getSubject();
           // User user = userRepo.findByNom(username);
//            if (user == null) {
//                return new ResponseEntity<>("no data", HttpStatus.NO_CONTENT);
//            } else {
//                return new ResponseEntity<User>(user,HttpStatus.OK);
//            }

        }
        return username;
    }



}
