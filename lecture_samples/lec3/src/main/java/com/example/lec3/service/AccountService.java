package com.example.lec3.service;

import com.example.lec3.domain.model.Account;
import com.example.lec3.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

}
