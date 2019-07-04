package com.anz.account.model;

import com.anz.account.types.Currency;
import com.anz.account.types.DebitCredit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.time.LocalDate;

@ApiModel(description = "Transaction Response")
public class TransactionRes  extends ResourceSupport {

    @ApiModelProperty(value = "Account number", example = "100-111-100")
    private String accountNumber;

    @ApiModelProperty(value = "Account Name", example = "Current Account")
    private String accountName;

    @ApiModelProperty(value = "Value Date", example = "2019-04-28")
    private LocalDate date;

    @ApiModelProperty(value = "Currency", example = "AUD")
    private Currency currency;

    @ApiModelProperty(value = "Debit Amount", example = "100.5")
    private BigDecimal debit;

    @ApiModelProperty(value = "Credit Amount", example = "1000.5")
    private BigDecimal credit;

    @ApiModelProperty(value = "Debit/credit ", example = "Credit")
    private DebitCredit debitCredit;

    @ApiModelProperty(value = "Transaction Narrative", example = "Transaction ref")
    private String narrative;


    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionRes accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public TransactionRes accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionRes date(LocalDate date) {
        this.date = date;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public TransactionRes currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public TransactionRes debit(BigDecimal debit) {
        this.debit = debit;
        return this;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public TransactionRes credit(BigDecimal credit) {
        this.credit = credit;
        return this;
    }

    public DebitCredit getDebitCredit() {
        return debitCredit;
    }

    public TransactionRes debitCredit(DebitCredit debitCredit) {
        this.debitCredit = debitCredit;
        return this;
    }

    public String getNarrative() {
        return narrative;
    }

    public TransactionRes narrative(String narrative) {
        this.narrative = narrative;
        return this;
    }

    @Override
    public String toString() {
        return "TransactionRes{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                ", date=" + date +
                ", currency=" + currency +
                ", debit=" + debit +
                ", credit=" + credit +
                ", debitCredit=" + debitCredit +
                ", narrative='" + narrative + '\'' +
                '}';
    }
}
