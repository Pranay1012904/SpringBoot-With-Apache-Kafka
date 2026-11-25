package com.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void produceToTopic(String message){
       CompletableFuture<SendResult<String, Object>> future= kafkaTemplate.send("test", message);
        future.whenComplete((r,e)->{
            if(e==null){
                System.out.println("SENT MESSAGE TO TOPIC: "+message+" OFFSET: "
                +r.getRecordMetadata().offset());
            }else{
                System.out.println("UNABLE TO PRODUCE MESSAGE TO TOPIC: "+e.getMessage());
            }
        });
    }
}
