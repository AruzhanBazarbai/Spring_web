package com.example.lec3.service;

import com.example.lec3.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandler {
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//    private final NewTopic topic1;
    @EventListener
    public void process(AccountCreatedEvent event){
        log.info("Event received: "+event);
//        kafkaTemplate.send("test_topic", event.getAggregateObjectId(),"test");
    }
}
