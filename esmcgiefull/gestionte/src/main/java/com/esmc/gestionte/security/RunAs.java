package com.esmc.gestionte.security;

import com.google.common.collect.ImmutableList;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("system", "system",
                ImmutableList.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        final Authentication originalAuthentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(token);
        func.run();
        SecurityContextHolder.getContext().setAuthentication(originalAuthentication);
    }

}