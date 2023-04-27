package com.example.bankserverproject.service;

import com.example.bankserverproject.domain.dto.TransactionDTO;
import com.example.bankserverproject.domain.model.Account;
import com.example.bankserverproject.domain.model.Transaction;
import com.example.bankserverproject.repository.AccountRepository;
import com.example.bankserverproject.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    //    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDto) {
        Long senderAccountId = transactionDto.getSenderAccountId();
        Long recipientAccountId = transactionDto.getRecipientAccountId();
        BigDecimal amount = transactionDto.getAmount();

        // Retrieve sender and recipient accounts from database
        Optional<Account> senderAccountOptional = accountRepository.findById(senderAccountId);
        Optional<Account> recipientAccountOptional = accountRepository.findById(recipientAccountId);

        if (senderAccountOptional.isPresent() && recipientAccountOptional.isPresent()) {
            Account senderAccount = senderAccountOptional.get();
            Account recipientAccount = recipientAccountOptional.get();
//            System.out.println(senderAccount);
            // Check if sender has sufficient balance
            if (senderAccount.getCurrentBalance().compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient balance in sender account");
            }
            // Update sender and recipient account balances
            senderAccount.setCurrentBalance(senderAccount.getCurrentBalance().subtract(amount));
            recipientAccount.setCurrentBalance(recipientAccount.getCurrentBalance().add(amount));
            System.out.println(senderAccount.getCurrentBalance() + " " + recipientAccount.getCurrentBalance());
            // Save updated accounts to the database
            accountRepository.save(senderAccount);
            accountRepository.save(recipientAccount);

            // Create and save transaction to the database
            Transaction transaction = new Transaction(senderAccount, recipientAccount, amount, LocalDateTime.now());
            transactionRepository.save(transaction);
            transactionDto.setId(transaction.getId());
            transactionDto.setTransactionTime(transaction.getCreatedDateTime());
            return transactionDto;
        } else {
            throw new IllegalArgumentException("Sender account or recipient account not found");
        }
    }

    public Optional<TransactionDTO> getTransactionById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            TransactionDTO transactionDto = new TransactionDTO(transaction.getId(),
                    transaction.getSenderAccount().getId(),
                    transaction.getRecipientAccount().getId(),
                    transaction.getAmount(),
                    transaction.getCreatedDateTime());
            return Optional.of(transactionDto);
        } else {
            return Optional.empty();
        }
    }

    public List<TransactionDTO> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return TransactionDTO.fromTransactionList(transactions);
    }



}
