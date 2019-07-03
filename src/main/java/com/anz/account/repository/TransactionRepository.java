package com.anz.account.repository;

import com.anz.account.domain.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    /**
     * Fetch transactions based on account number joined on account table.
     *
     */
    @Query("SELECT trn FROM Transaction trn INNER JOIN trn.account a where a.accountNumber = ?1")
    List<Transaction> findByAccountNumber(String accountNumber, Pageable page);
}
