package com.anz.account.service;

import com.anz.account.domain.Account;
import com.anz.account.model.AccountRes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    List<AccountRes> getUserAccounts(String userId, Pageable page);

    Account getAccountByAccountNumber(String accountNumber);

}
