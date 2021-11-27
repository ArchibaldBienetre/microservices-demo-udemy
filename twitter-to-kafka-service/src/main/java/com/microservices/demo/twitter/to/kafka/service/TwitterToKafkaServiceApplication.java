package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.init.StreamInitializer;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
// find Spring beans in other modules
@ComponentScan(basePackages = "com.microservices.demo")
@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    // Could also use field injection, BUT:
    // * then you have to use Autowired,
    // * you have to make it non-final,
    // * it is not enforced that the variable gets a value
    // * Spring uses reflection for this (resolved at runtime) - slower!
    // @Autowired
    private final StreamRunner streamRunner;
    private final StreamInitializer initializer;

    // Constructor injection does not need @Autowired here!
    public TwitterToKafkaServiceApplication(StreamRunner streamRunner, StreamInitializer initializer) {
        this.streamRunner = streamRunner;
        this.initializer = initializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("App starts...");
        initializer.init();
        streamRunner.start();
    }

}
