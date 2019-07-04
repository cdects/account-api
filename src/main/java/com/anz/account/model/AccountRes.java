package com.anz.account.model;

import com.anz.account.types.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.time.LocalDate;


@ApiModel(description = "Account Response")
public class AccountRes extends ResourceSupport {

    @ApiModelProperty(value = "Account number", example = "A10001")
    private String accountNumber;

    @ApiModelProperty(value = "Account Name", example = "AUAccount")
    private String accountName;

    @ApiModelProperty(value = "Account Type", example = "Savings")
    private String accountType;

    @ApiModelProperty(value = "Account Balance Date", example = "2019-04-28")
    private LocalDate balanceDate;

    @ApiModelProperty(value = "Currency", example = "AUD")
    private Currency currency;

    @ApiModelProperty(value = "Available balance", example = "10101.0")
    private BigDecimal openingAvailableBalance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountRes accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountRes accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public AccountRes accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public LocalDate getBalanceDate() {
        return balanceDate;
    }

    public AccountRes balanceDate(LocalDate balanceDate) {
        this.balanceDate = balanceDate;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public AccountRes currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getOpeningAvailableBalance() {
        return openingAvailableBalance;
    }

    public AccountRes openingAvailableBalance(BigDecimal openingAvailableBalance) {
        this.openingAvailableBalance = openingAvailableBalance;
        return this;
    }

    @Override
    public String toString() {
        return "AccountRes{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balanceDate=" + balanceDate +
                ", currency=" + currency +
                ", openingAvailableBalance=" + openingAvailableBalance +
                '}';
    }
}
