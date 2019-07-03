package com.anz.account.types;

public enum DebitCredit {
    DEBIT("Debit"),
    CREDIT("Credit");
    final String debitCredit;

    DebitCredit(final String debitCredit){ this.debitCredit = debitCredit; }

    public String getDebitCredit(){ return  debitCredit;}
}
