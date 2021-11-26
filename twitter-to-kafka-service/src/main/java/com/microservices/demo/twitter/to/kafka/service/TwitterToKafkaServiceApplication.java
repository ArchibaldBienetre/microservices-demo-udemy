package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
// import org.springframework.context.ApplicationEvent;
// import org.springframework.context.ApplicationListener;
// import org.springframework.context.annotation.Scope;

// import javax.annotation.PostConstruct;

// === initialization logic: 3 options ==

// option 1
// @Scope("singleton")
// @SpringBootApplication
// public class TwitterToKafkaServiceApplication {

// option 2
//@SpringBootApplication
//public class TwitterToKafkaServiceApplication implements ApplicationListener {

@Slf4j
// option 3
@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    // Could also use field injection, BUT:
    // * then you have to use Autowired,
    // * you have to make it non-final,
    // * it is not enforced that the variable gets a value
    // * Spring uses reflection for this (resolved at runtime) - slower!
    // @Autowired
    // private TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final StreamRunner streamRunner;

    // Constructor injection does not need @Autowired here!
    public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData, StreamRunner streamRunner) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
        this.streamRunner = streamRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    // option 1
//    @PostConstruct
//    public void init() {
//        // ...
//    }

    // option 2
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        // ...
//    }

    // option 3
    @Override
    public void run(String... args) throws Exception {
        log.info("App starts...");
        log.info("Twitter keywords: {}", Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray()));
        log.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
        streamRunner.start();
    }

}
