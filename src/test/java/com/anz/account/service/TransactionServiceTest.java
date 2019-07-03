package com.anz.account.service;

import com.anz.account.domain.Account;
import com.anz.account.domain.Transaction;
import com.anz.account.model.TransactionRes;
import com.anz.account.repository.TransactionRepository;
import com.anz.account.types.AccountType;
import com.anz.account.types.Currency;
import com.anz.account.types.DebitCredit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        public TransactionService transactionService() {
            return new TransactionServiceImpl();
        }
    }

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void getTransactionsTest_NoTransactionsForAccount() {
        Mockito.when(transactionRepository.findByAccountNumber("a1009", PageRequest.of(0, 20)))
                .thenReturn(Collections.emptyList());
        List<TransactionRes> transactions = transactionService.getTransactions("a1009", PageRequest.of(0, 20));

        assertThat(transactions.size()).isEqualTo(0);
    }

    @Test
    public void getTransactionsTest_happyPath() {
        Mockito.when(transactionRepository.findByAccountNumber("a10001", PageRequest.of(0, 20)))
                .thenReturn(mockTransactions());
        List<TransactionRes> transactions = transactionService.getTransactions("a10001", PageRequest.of(0, 20));

        assertThat(transactions.size()).isEqualTo(4);
    }

    private List<Transaction> mockTransactions(){

        Account account1 = new Account("a10001",
                "AUDSaving1",
                AccountType.SAVING,
                LocalDate.of(2019, Month.APRIL, 28),
                Currency.AUD,
                BigDecimal.valueOf(10000.10),
                "u001");

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
                account1);
        return Arrays.asList(t1, t2,t3,t4);
    }

}
