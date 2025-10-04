
package com.example.bank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Domain entity representing a bank account.
 * Invariants:
 *  - holderName is non-null/non-blank
 *  - balance is non-negative
 */
public final class Account {
    private final String id;
    private final String holderName;
    private BigDecimal balance;

    public Account(String holderName, BigDecimal initialBalance) {
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name must not be blank");
        }
        if (initialBalance == null || initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance must be non-negative");
        }
        this.id = UUID.randomUUID().toString();
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public String getHolderName() {
        return holderName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    void increase(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    void decrease(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
