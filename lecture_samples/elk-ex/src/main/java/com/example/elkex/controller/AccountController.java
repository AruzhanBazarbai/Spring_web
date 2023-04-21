package com.example.elkex.controller;

import com.example.elkex.domain.dto.AccountDTO;
import com.example.elkex.domain.dto.CreateAccountDTO;
import com.example.elkex.domain.model.Account;
import com.example.elkex.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/user/accounts")
    public List<Account> findAll(){
        return accountService.findAllAccounts();
    }
    @PostMapping("/user/accounts")
    public Account createAccount(@RequestBody CreateAccountDTO createAccountDTO) throws JsonProcessingException {
        return accountService.createAccount(createAccountDTO);
    }
}
