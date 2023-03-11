package com.example.sis6.service;

import com.example.sis6.domain.dto.CreateAccountDTO;
import com.example.sis6.domain.event.AccountCreatedEvent;
import com.example.sis6.domain.event.AccountDeletedEvent;
import com.example.sis6.domain.event.AccountUpdatedEvent;
import com.example.sis6.domain.model.Account;
import com.example.sis6.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccount(long id){
        Optional<Account> tempAccount=accountRepository.findById(id);
        if(tempAccount.isPresent()){
            Account account=tempAccount.get();
            return account;
        }else{
            return null;
        }

    }

    public Account createAccount( CreateAccountDTO createAccountDTO) throws JsonProcessingException {
        Account account=new Account();
        account.setUsername(createAccountDTO.getUsername());
        account.setEmail(createAccountDTO.getEmail());
        account.setPassword(createAccountDTO.getPassword());
        Account savedAccount=accountRepository.save(account);

        AccountCreatedEvent event=new AccountCreatedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(savedAccount.getId()));

        ObjectMapper mapper=new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString(savedAccount));

        eventPublisher.publishEvent(event);
        return savedAccount;
    }

    public Account updateAccount(long id, CreateAccountDTO createAccountDTO) throws JsonProcessingException {
        Account account=getAccount(id);
        if(account!=null){
            account.setUsername(createAccountDTO.getUsername());
            account.setEmail(createAccountDTO.getEmail());
            account.setPassword(createAccountDTO.getPassword());

            Account savedAccount= accountRepository.save(account);

            AccountUpdatedEvent event=new AccountUpdatedEvent();
            event.setAggregateObjectId(String.valueOf(id));
            event.setAggregateObjectType("Account");
            event.setEmittedDate(LocalDateTime.now());
            event.setMessagePayload(new ObjectMapper().writeValueAsString(savedAccount));

            eventPublisher.publishEvent(event);
            return savedAccount;
        }else{
            return null;
        }


    }
    public Optional<Exception> deleteAccount(long id){
//        Account account=getAccount(id);
        try{
            accountRepository.deleteById(id);
            AccountDeletedEvent event =new AccountDeletedEvent();
            event.setAggregateObjectId(String.valueOf(id));
            event.setEmittedDate(LocalDateTime.now());

            event.setMessagePayload(String.valueOf(id));
            event.setAggregateObjectType("Account");
            eventPublisher.publishEvent(event);
            return Optional.of(null);
        }catch (Exception e){
            return Optional.of(new Exception());
        }
    }

//    @KafkaListener(topics = "AccountTest1",containerFactory = "filterKafkaListenerContainerFactoryByUpdated")
//    public void listenWithFilterUpdated(String message) {
//        System.out.println("Received Message in group Updated: " + message);
//    }
//    @KafkaListener(topics = "AccountTest1",containerFactory = "filterKafkaListenerContainerFactoryByCreated")
//    public void listenWithFilterCreated(String message) {
//        System.out.println("Received Message in group Created: " + message);
//    }
//    @KafkaListener(topics = "AccountTest1",containerFactory = "filterKafkaListenerContainerFactoryByDeleted")
//    public void listenWithFilterDeleted(String message) {
//        System.out.println("Received Message in group Deleted: " + message);
//    }
    @KafkaListener(topics = "AccountTest1")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

}