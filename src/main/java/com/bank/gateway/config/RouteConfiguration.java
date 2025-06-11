package com.bank.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator authCircuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_service", p -> p
                        .path("/auth/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("authCircuitBreaker")
                                .setFallbackUri("forward:/fallback/authFallback")))
                        .uri("lb://auth-service"))
                .build();
    }

    @Bean
    public RouteLocator userCircuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_service", p -> p
                        .path("/user/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("user")
                                .setFallbackUri("forward:/fallback/userFallback")))
                        .uri("lb://user-service"))
                .build();
    }

    @Bean
    public RouteLocator paymentCircuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("payment_service", p -> p
                        .path("/payment/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("payment")
                                .setFallbackUri("forward:/fallback/paymentFallback")))
                        .uri("lb://PAYMENT-SERVICE"))
                .build();
    }

    @Bean
    public RouteLocator accountCircuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account_route", p -> p
                        .path("/account/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("accountCircuitBreaker")
                                .setFallbackUri("forward:/fallback/accountFallback")))
                        .uri("lb://ACCOUNT-SERVICE"))
                .build();
    }
}
