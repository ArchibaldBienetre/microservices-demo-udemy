package com.microservices.demo.twitter.to.kafka.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

// To mark Spring-managed beans:
// @Component, @Controller (API), @Service (Business Layer), @Repository (Data Layer), @Configuration (config classes)
// (scanned and loaded by spring at runtime)
@Component
@Slf4j
public class TwitterKafkaStatusListener extends StatusAdapter {
    @Override
    public void onStatus(Status status) {
        log.info("Twitter status with text '{}'", status.getText());
    }
}
