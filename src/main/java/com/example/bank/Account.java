package com.example.bank;

import java.math.BigDecimal;

public class Account {
    private final String id;
    private final String owner;
    private BigDecimal balance;

    public Account(String id, String owner, BigDecimal openingBalance) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id is required");
        if (owner == null || owner.isBlank()) throw new IllegalArgumentException("owner is required");
        if (openingBalance == null || openingBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("openingBalance must be >= 0");
        this.id = id;
        this.owner = owner;
        this.balance = openingBalance;
    }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public BigDecimal getBalance() { return balance; }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidAmountException("Deposit must be > 0");
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidAmountException("Withdrawal must be > 0");
        if (balance.compareTo(amount) < 0)
            throw new InsufficientFundsException("Not enough funds");
        balance = balance.subtract(amount);
    }
}
