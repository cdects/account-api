package com.anz.account.bootstrap;


import com.anz.account.domain.Account;
import com.anz.account.domain.Transaction;
import com.anz.account.repository.TransactionRepository;
import com.anz.account.types.AccountType;
import com.anz.account.types.Currency;
import com.anz.account.types.DebitCredit;
import com.anz.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

/**
 * This class is loading data in H2 on application startup for testing purpose.
 * It is not required for production code.
 */

@Component
public class ApplicationInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    AccountRepository accountsRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        Account account1 = new Account("a10001",
                "AUDSaving1",
                AccountType.SAVING,
                LocalDate.of(2019, Month.APRIL, 28),
                Currency.AUD,
                BigDecimal.valueOf(10000.10),
                "u001");

        Account account2 = new Account("a10002",
                "AUDCurrent2",
                AccountType.CURRENT,
                LocalDate.of(2019, Month.APRIL, 29),
                Currency.AUD,
                BigDecimal.valueOf(10001.10),
                "u002");

        Transaction t1 = new Transaction(1L,
                LocalDate.of(2019, Month.APRIL, 28 ),
                Currency.AUD,
                BigDecimal.ZERO,
                BigDecimal.valueOf(50),
                DebitCredit.DEBIT,
                "transaction description",
                account1);

        Transaction t2 = new Transaction(2l,
                LocalDate.of(2019, Month.APRIL, 28 ),
                Currency.AUD,
                BigDecimal.ZERO,
                BigDecimal.valueOf(150),
                DebitCredit.CREDIT,
                "transaction description",
                account1);

        Transaction t3 = new Transaction(3l,
                LocalDate.of(2019, Month.APRIL, 29 ),
                Currency.AUD,
                BigDecimal.valueOf(50),
                BigDecimal.ZERO,
                DebitCredit.DEBIT ,
                "transaction description",
                account1);

        Transaction t4 = new Transaction(4l,
                LocalDate.of(2019, Month.APRIL, 30 ),
                Currency.AUD,
                BigDecimal.valueOf(50),
                BigDecimal.ZERO,
                DebitCredit.DEBIT,
                "transaction description",
                account2);

        accountsRepository.saveAll(Arrays.asList(account1, account2));
        transactionRepository.saveAll(Arrays.asList(t1, t2,t3,t4));
    }
}
