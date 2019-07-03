package com.anz.account.service;

import com.anz.account.domain.Transaction;
import com.anz.account.model.TransactionRes;
import com.anz.account.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionRes> getTransactions(String accountNumber, Pageable page) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber, page);

        return Optional.ofNullable(transactions).orElseGet(ArrayList::new)
                .stream()
                .map(this::mapToTransactionRes)
                .collect(Collectors.toList());
    }

    private TransactionRes mapToTransactionRes(Transaction transaction){
        TransactionRes transactionRes = new TransactionRes()
         .accountNumber(transaction.getAccount().getAccountNumber())
        .accountName(transaction.getAccount().getAccountName())
        .date(transaction.getDate())
        .debitCredit(transaction.getDebitCredit())
        .debit(transaction.getDebit())
        .credit(transaction.getCredit())
        .currency(transaction.getCurrency())
        .narrative(transaction.getNarrative());
        return transactionRes;
    }

}
