package com.example.bank;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String id, String owner, BigDecimal openingBalance) {
        if (accounts.containsKey(id)) {
            throw new IllegalArgumentException("Account already exists: " + id);
        }
        Account acc = new Account(id, owner, openingBalance);
        accounts.put(id, acc);
        return acc;
    }

    public Account getAccount(String id) {
        Account acc = accounts.get(id);
        if (acc == null) throw new AccountNotFoundException("Account not found: " + id);
        return acc;
    }

    public void transfer(String fromId, String toId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidAmountException("Transfer must be > 0");
        Account from = getAccount(fromId);
        Account to = getAccount(toId);
        from.withdraw(amount);
        to.deposit(amount);
    }

    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
}
