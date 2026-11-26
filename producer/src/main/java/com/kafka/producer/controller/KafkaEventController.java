package com.kafka.producer.controller;

import com.kafka.producer.service.KafkaPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class KafkaEventController {

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> sendMessage(@PathVariable String message){
        try {
            for(int i=1;i<10000;i++) {
                kafkaPublisher.produceToTopic(message+" ID :: "+i);
            }
            return new ResponseEntity<>("Message Published", HttpStatus.OK);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
