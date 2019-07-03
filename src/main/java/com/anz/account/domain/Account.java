package com.anz.account.domain;

import com.anz.account.types.AccountType;
import com.anz.account.types.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Account{
    @Id
    @Column(name="accountId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountName;
    private AccountType type;
    private LocalDate date;
    private Currency currency;
    private BigDecimal balance;
    private String userId;

    public Account(){}

    public Account(String accountNumber, String accountName, AccountType type, LocalDate date, Currency currency, BigDecimal balance, String userId) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.type = type;
        this.date = date;
        this.currency = currency;
        this.balance = balance;
        this.userId = userId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
