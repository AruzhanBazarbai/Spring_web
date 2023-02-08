package com.example.lec3.repository;

import com.example.lec3.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository  extends JpaRepository<Account, Long>{

}
