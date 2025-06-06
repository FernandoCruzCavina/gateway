package com.bank.gateway.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

public class JwtClaimAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final String claimName;
    private final boolean expectedValue;

    public JwtClaimAuthorizationManager(String claimName, boolean expectedValue) {
        this.claimName = claimName;
        this.expectedValue = expectedValue;
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
            .filter(auth -> auth.isAuthenticated() && auth.getPrincipal() instanceof Jwt)
            .map(auth -> {
                Jwt jwt = (Jwt) auth.getPrincipal();
                Boolean claimValue = jwt.getClaim(claimName);
                return new AuthorizationDecision(Boolean.TRUE.equals(claimValue) == expectedValue);
            });
    }
}