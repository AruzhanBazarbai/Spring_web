package com.example.sis6.service;

import com.example.sis6.domain.event.AccountCreatedEvent;
import com.example.sis6.domain.event.AccountDeletedEvent;
import com.example.sis6.domain.event.AccountUpdatedEvent;
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

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private final NewTopic topic1;
    @EventListener
    public void process(AccountCreatedEvent event){
        log.info("Event received: "+event+event.getClass().getName());
        kafkaTemplate.send(topic1.name(), event.getAggregateObjectId(),"Created");
    }
    @EventListener
    public void process(AccountUpdatedEvent event){
        log.info("Event received: "+event+event.getClass().getName());
        kafkaTemplate.send(topic1.name(), event.getClass().getName(),"Updated");
    }
    @EventListener
    public void process(AccountDeletedEvent event){
        log.info("Event received: "+event+event.getClass().getName());
        kafkaTemplate.send(topic1.name(), event.getClass().getName(),"Deleted");
    }
}
