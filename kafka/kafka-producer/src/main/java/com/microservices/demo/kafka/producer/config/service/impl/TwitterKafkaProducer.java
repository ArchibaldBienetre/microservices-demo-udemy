package com.microservices.demo.kafka.producer.config.service.impl;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.producer.config.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;

@Slf4j
@Service
public class TwitterKafkaProducer implements KafkaProducer<Long, TwitterAvroModel> {

    private final KafkaTemplate<Long, TwitterAvroModel> template;

    public TwitterKafkaProducer(KafkaTemplate<Long, TwitterAvroModel> template) {
        this.template = template;
    }

    @Override
    public void send(String topicName, Long key, TwitterAvroModel message) {
        log.info("Sending message='{}' to topic='{}'", message, topicName);
        ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture
                = template.send(topicName, key, message);
        addCallback(topicName, message, kafkaResultFuture);
    }

    private void addCallback(String topicName, TwitterAvroModel message, ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture) {
        kafkaResultFuture.addCallback(new ListenableFutureCallback<SendResult<Long, TwitterAvroModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Error while sending message '{}' on topic '{}'", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, TwitterAvroModel> longTwitterAvroModelSendResult) {
                RecordMetadata metadata = longTwitterAvroModelSendResult.getRecordMetadata();
                log.debug("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp: {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        });
    }

    @PreDestroy
    public void close() {
        if (template != null) {
            log.info("Closing Kafka producer!");
            template.destroy();
        }
    }
}
