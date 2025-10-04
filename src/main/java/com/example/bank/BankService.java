
package com.example.bank;

import java.math.BigDecimal;

/**
 * Application service containing business rules:
 *  - deposit/withdraw validate amounts (> 0)
 *  - withdraw cannot exceed balance
 */
public class BankService {

    public Account createAccount(String holderName, BigDecimal initialBalance) {
        return new Account(holderName, initialBalance);
    }

    public void deposit(Account account, BigDecimal amount) {
        requireAccount(account);
        requirePositive(amount);
        account.increase(amount);
    }

    public void withdraw(Account account, BigDecimal amount) {
        requireAccount(account);
        requirePositive(amount);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        account.decrease(amount);
    }

    private void requireAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("Account must not be null");
    }

    private void requirePositive(BigDecimal amount) {
        if (amount == null) throw new IllegalArgumentException("Amount must not be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}
