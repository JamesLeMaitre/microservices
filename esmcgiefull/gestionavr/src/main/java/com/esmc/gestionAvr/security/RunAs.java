package com.esmc.gestionAvr.security;

import com.google.common.collect.ImmutableList;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class RunAs {

    @FunctionalInterface
    public interface RunAsMethod {
        default void run() {
            try {
                runWithException();
            } catch (Exception e) {
                // ignore
            }
        }
        void runWithException() throws Exception;
    }

    public static void runAsAdmin(final RunAsMethod func) {
        func.run();
    }

}