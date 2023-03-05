package com.example.sis45.service;

import com.example.sis45.domain.dto.CreateAccountDTO;
import com.example.sis45.domain.event.AccountCreatedEvent;
import com.example.sis45.domain.event.AccountDeletedEvent;
import com.example.sis45.domain.event.AccountEvent;
import com.example.sis45.domain.event.AccountUpdatedEvent;
import com.example.sis45.domain.model.Account;
import com.example.sis45.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
        event.setAgregateObjectType("Account");
        event.setAgregateObjectId(String.valueOf(savedAccount.getId()));

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
                event.setAgregateObjectId(String.valueOf(id));
                event.setAgregateObjectType("Account");
                event.setEmittedDate(LocalDateTime.now());
                event.setMessagePayload(new ObjectMapper().writeValueAsString(savedAccount));

                eventPublisher.publishEvent(event);
                return savedAccount;
            }else{
                return null;
            }


    }
    public Optional<Exception> deleteAccount(long id){
        Account account=getAccount(id);
        try{
           accountRepository.deleteById(id);
           AccountDeletedEvent event =new AccountDeletedEvent();
           event.setAgregateObjectId(String.valueOf(id));
           event.setEmittedDate(LocalDateTime.now());

           event.setMessagePayload(new ObjectMapper().writeValueAsString(account));
           event.setAgregateObjectType("Account");
           eventPublisher.publishEvent(event);
           return Optional.of(null);
        }catch (Exception e){
            return Optional.of(new Exception());
        }
    }

}
