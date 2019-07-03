package com.anz.account.service;

import com.anz.account.domain.Account;
import com.anz.account.model.AccountRes;
import com.anz.account.repository.AccountRepository;
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
public class AccountServiceImpl  implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountRes> getUserAccounts(String userId, Pageable page) {
        List<Account> accounts = accountRepository.findAllByUserId(userId, page);

        return Optional.ofNullable(accounts).orElseGet(ArrayList::new)
                .stream()
                .map(this::mapToAccountRes)
                .collect(Collectors.toList());
     }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    private AccountRes mapToAccountRes(Account account){
        AccountRes accountRes = new AccountRes()
                .accountType(account.getType().name())
                .accountName(account.getAccountName())
                .accountNumber(account.getAccountNumber())
                .currency(account.getCurrency())
                .balanceDate(account.getDate())
                .openingAvailableBalance(account.getBalance());
        return accountRes;
    }
}
