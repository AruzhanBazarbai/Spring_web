package com.example.elkex.service;

import com.example.elkex.domain.dto.AccountDTO;
import com.example.elkex.domain.dto.CreateAccountDTO;
import com.example.elkex.domain.event.AccountCreatedEvent;
import com.example.elkex.domain.model.Account;
import com.example.elkex.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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
    public Account createAccount( CreateAccountDTO createAccountDTO) throws JsonProcessingException {
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
//        AccountDTO result=new AccountDTO(
//                savedAccount.getUsername(),
//                savedAccount.getPassword(),
//                savedAccount.getEmail(),
//                savedAccount.getLast_login());
        return savedAccount;
    }

}