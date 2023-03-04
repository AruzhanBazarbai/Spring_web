package com.example.lec3.controller;

import com.example.lec3.domain.dto.CreateAccountDTO;
import com.example.lec3.domain.model.Account;
import com.example.lec3.service.AccountService;
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
    @GetMapping("/accounts")
    public List<Account> findAll(){
        return accountService.findAllAccounts();
    }
    @PostMapping("/accounts")
    public Account createAccount(@RequestBody CreateAccountDTO createAccountDTO) throws JsonProcessingException {
        return accountService.createAccount(createAccountDTO);
    }
}
