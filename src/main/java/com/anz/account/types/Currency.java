package com.anz.account.types;

public enum Currency {
    AUD("AUD"),
    SDG("SGD");

    private String currency;

    Currency(String currency){
        this.currency = currency;
    }

    public String getCurrency() {
        return this.currency;
    }
}
