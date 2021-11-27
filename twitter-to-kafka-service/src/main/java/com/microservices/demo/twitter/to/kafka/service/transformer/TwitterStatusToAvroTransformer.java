package com.microservices.demo.twitter.to.kafka.service.transformer;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
public class TwitterStatusToAvroTransformer {

    public TwitterAvroModel getTwitterAvroModelFromStatus(Status status) {
        return TwitterAvroModel.newBuilder()
                .setUserId(status.getUser().getId())
                .setId(status.getId())
                .setText(status.getText())
                .setCreatedAt(status.getCreatedAt().getTime())
                .build();
    }
}
