package com.example.sis45.controller;

import com.example.sis45.domain.dto.CreateAccountDTO;
import com.example.sis45.domain.model.Account;
import com.example.sis45.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody CreateAccountDTO createAccountDTO) throws JsonProcessingException {
        Account account=accountService.updateAccount(id,createAccountDTO);
        if(account!=null){
          return new ResponseEntity<>(account,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping("accounts/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable long id){
        try{
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
