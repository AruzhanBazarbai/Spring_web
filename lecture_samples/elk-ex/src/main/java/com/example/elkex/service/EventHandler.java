package com.example.elkex.service;

import com.example.elkex.domain.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandler {
    @EventListener
    public void process(AccountCreatedEvent event){
        log.info("Event received: "+event);
    }
}
