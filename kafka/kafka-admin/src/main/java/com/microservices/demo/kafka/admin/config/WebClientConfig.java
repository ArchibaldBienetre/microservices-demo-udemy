package com.microservices.demo.kafka.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// @Configuration => class has @Bean methods to create Spring beans
@Configuration
public class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
}
