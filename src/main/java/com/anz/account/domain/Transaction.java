package com.anz.account.domain;

import com.anz.account.types.Currency;
import com.anz.account.types.DebitCredit;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Currency currency;
    private BigDecimal debit;
    private BigDecimal credit;
    private DebitCredit debitCredit;
    private String narrative;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public Transaction() {
    }


    public Transaction(Long id, LocalDate date, Currency currency, BigDecimal debit, BigDecimal credit, DebitCredit debitCredit, String narrative, Account account) {
        this.id = id;
        this.date = date;
        this.currency = currency;
        this.debit = debit;
        this.credit = credit;
        this.debitCredit = debitCredit;
        this.narrative = narrative;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public DebitCredit getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(DebitCredit debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
