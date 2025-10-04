
package com.example.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    @DisplayName("Deposit increases balance (positive scenario)")
    void depositIncreasesBalance() {
        BankService bank = new BankService();
        Account acc = bank.createAccount("Alice", new BigDecimal("100.00"));
        bank.deposit(acc, new BigDecimal("25.50"));
        assertEquals(new BigDecimal("125.50"), acc.getBalance());
        assertTrue(acc.getBalance().compareTo(new BigDecimal("100")) > 0, "Balance should be greater than 100");
    }

    @Test
    @DisplayName("Withdraw decreases balance until funds are exhausted (positive scenario)")
    void withdrawDecreasesBalance() {
        BankService bank = new BankService();
        Account acc = bank.createAccount("Bob", new BigDecimal("80.00"));
        bank.withdraw(acc, new BigDecimal("30.00"));
        assertAll(
            () -> assertEquals(new BigDecimal("50.00"), acc.getBalance()),
            () -> assertFalse(acc.getBalance().compareTo(new BigDecimal("50.00")) < 0, "Balance should not be negative")
        );
    }

    @Test
    @DisplayName("Withdrawing more than balance throws InsufficientFundsException (negative scenario)")
    void withdrawingTooMuchFails() {
        BankService bank = new BankService();
        Account acc = bank.createAccount("Carol", new BigDecimal("10.00"));
        assertThrows(InsufficientFundsException.class, () -> bank.withdraw(acc, new BigDecimal("11.00")));
    }

    @Test
    @DisplayName("Reject zero/negative amounts for deposit/withdraw (negative scenarios)")
    void rejectNonPositiveAmounts() {
        BankService bank = new BankService();
        Account acc = bank.createAccount("Dan", new BigDecimal("5.00"));

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> bank.deposit(acc, new BigDecimal("0"))),
            () -> assertThrows(IllegalArgumentException.class, () -> bank.deposit(acc, new BigDecimal("-1"))),
            () -> assertThrows(IllegalArgumentException.class, () -> bank.withdraw(acc, new BigDecimal("0"))),
            () -> assertThrows(IllegalArgumentException.class, () -> bank.withdraw(acc, new BigDecimal("-1")))
        );
    }
}
