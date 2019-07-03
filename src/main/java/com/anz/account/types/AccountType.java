package com.anz.account.types;

public enum AccountType {
    SAVING("Saving"),
    CURRENT("Current");

    final String type;

    AccountType(final String type){ this.type = type; }

    public String getType(){ return  type;}
}
