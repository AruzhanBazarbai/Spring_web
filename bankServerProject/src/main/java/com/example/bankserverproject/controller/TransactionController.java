package com.example.bankserverproject.controller;

import com.example.bankserverproject.domain.dto.TransactionDTO;
import com.example.bankserverproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDto) {
        TransactionDTO createdTransaction = transactionService.createTransaction(transactionDto);
        log.info("transaction created: {}", createdTransaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("id") Long id) {
        Optional<TransactionDTO> transactionDto = transactionService.getTransactionById(id);
        if (transactionDto.isPresent()) {
            log.info("Get transaction with ID: {}", transactionDto);
            return new ResponseEntity<>(transactionDto.get(), HttpStatus.OK);
        } else {
            log.info("There is no transaction with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactionDtos = transactionService.findAllTransactions();
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

}
