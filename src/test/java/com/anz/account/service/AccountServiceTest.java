package com.anz.account.service;

import com.anz.account.domain.Account;
import com.anz.account.model.AccountRes;
import com.anz.account.repository.AccountRepository;
import com.anz.account.types.AccountType;
import com.anz.account.types.Currency;
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
public class AccountServiceTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;


    @Test
    public void getAccountByAccountNumber_happyPath() {
        Mockito.when(accountRepository.findByAccountNumber("u001"))
                .thenReturn(mockAccounts().get(0));
        Account account = accountService.getAccountByAccountNumber("u001");
        assertThat(account.getUserId()).isEqualTo("u001");
        assertThat(account.getCurrency()).isEqualTo(Currency.AUD);
        assertThat(account.getAccountName()).isEqualTo("AUDSaving1");
    }

    @Test
    public void getAccountByAccountNumber_accountDoesNotExist() {
        Mockito.when(accountRepository.findByAccountNumber("u003"))
                .thenReturn(null);
        Account account = accountService.getAccountByAccountNumber("u003");
        assertThat(account).isNull();
    }

    @Test
    public void getUserAccountsTest_userDoesNotExist() {
        Mockito.when(accountRepository.findAllByUserId("999", PageRequest.of(0, 20)))
                .thenReturn(Collections.emptyList());
        List<AccountRes> accounts = accountService.getUserAccounts("999", PageRequest.of(0, 20));
        assertThat(accounts.size()).isEqualTo(0);
    }

    @Test
    public void getUserAccountsTest_HappyPath() {
        Mockito.when(accountRepository.findAllByUserId("u001", PageRequest.of(0, 20)))
                .thenReturn(mockAccounts());
        List<AccountRes> accounts = accountService.getUserAccounts("u001", PageRequest.of(0, 20));
        assertThat(accounts.size()).isGreaterThan(0);
    }




   private List<Account>  mockAccounts(){
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
                "u001");

      return Arrays.asList(account1, account2);
    }

}
