package com.kafka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageListener {

    @KafkaListener(topics = "customTopic_1", groupId = "consumer_group_1")
    public void consumeMessage(String message){
        log.info("consume message:{}",message);
    }
}
