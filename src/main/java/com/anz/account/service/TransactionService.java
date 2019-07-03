package com.anz.account.service;

import com.anz.account.model.TransactionRes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    List<TransactionRes> getTransactions(String accountNumber, Pageable page);
}
