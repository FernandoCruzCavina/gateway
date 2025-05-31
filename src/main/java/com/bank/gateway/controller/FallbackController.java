package com.bank.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/{service}")
    public Mono<ResponseEntity<String>> fallbackGet(@PathVariable("service") String serviceName) {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("This " + serviceName + " service temporarily unavailable."));
    }

    @PostMapping("/{service}")
    public Mono<ResponseEntity<String>> fallbackPost(@PathVariable("service") String serviceName) {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("This " + serviceName + " service temporarily unavailable."));
    }

    @PutMapping("/{service}")
    public Mono<ResponseEntity<String>> fallbackUpdate(@PathVariable("service") String serviceName) {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("This " + serviceName + " service temporarily unavailable."));
    }

    @DeleteMapping("/{service}")
    public Mono<ResponseEntity<String>> fallbackMethod(@PathVariable("service") String serviceName) {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("This " + serviceName + " service temporarily unavailable."));
    }

}
