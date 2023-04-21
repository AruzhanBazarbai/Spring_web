package com.example.lec3.service;

import com.example.lec3.domain.dto.AccountDTO;
import com.example.lec3.domain.dto.CreateAccountDTO;
import com.example.lec3.domain.event.AccountCreatedEvent;
import com.example.lec3.domain.model.Account;
import com.example.lec3.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }
    public AccountDTO createAccount( CreateAccountDTO createAccountDTO) throws JsonProcessingException {
        Account account=new Account();
        account.setUsername(createAccountDTO.getUsername());
        account.setEmail(createAccountDTO.getEmail());
        account.setPassword(createAccountDTO.getPassword());
        account.setLast_login(createAccountDTO.getLoginDate());
        Account savedAccount=accountRepository.save(account);

        AccountCreatedEvent event=new AccountCreatedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(savedAccount.getId()));

        ObjectMapper mapper=new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString(savedAccount));

        eventPublisher.publishEvent(event);
        AccountDTO result=new AccountDTO(
                savedAccount.getUsername(),
                savedAccount.getPassword(),
                savedAccount.getEmail(),
                savedAccount.getLast_login());
        return result;
    }
//    @KafkaListener(topicPattern = "my_super_topic")
//    public void listenGroupFoo(String message) {
//        System.out.println("Received Message in group foo: " + message);
//    }

}
