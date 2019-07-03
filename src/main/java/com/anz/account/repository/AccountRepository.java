package com.anz.account.repository;

import com.anz.account.domain.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAllByUserId(String userId, Pageable page);

    Account findByAccountNumber(String accountNumber);
}
