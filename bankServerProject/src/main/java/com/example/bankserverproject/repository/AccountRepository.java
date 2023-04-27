package com.example.bankserverproject.repository;

import com.example.bankserverproject.domain.model.Account;
import com.example.bankserverproject.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByCustomer(Customer customer);
}
