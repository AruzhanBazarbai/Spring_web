package com.example.sis45.service;

import com.example.sis45.domain.event.AccountCreatedEvent;
import com.example.sis45.domain.event.AccountDeletedEvent;
import com.example.sis45.domain.event.AccountEvent;
import com.example.sis45.domain.event.AccountUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventHandler {
    @EventListener
    public void process(AccountCreatedEvent event){
        log.info("Event received: "+event);
    }
    @EventListener
    public void process(AccountUpdatedEvent event){
        log.info("Event received: "+event);
    }
    @EventListener
    public void process(AccountDeletedEvent event){
        log.info("Event received: "+event);
    }

}
