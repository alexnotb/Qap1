package com.example.bank;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void depositIncreasesBalance() {
        Account acc = new Account("1", "Alex", BigDecimal.valueOf(100));
        acc.deposit(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(150), acc.getBalance());
    }

    @Test
    void withdrawDecreasesBalance() {
        Account acc = new Account("1", "Alex", BigDecimal.valueOf(100));
        acc.withdraw(BigDecimal.valueOf(40));
        assertEquals(BigDecimal.valueOf(60), acc.getBalance());
    }

    @Test
    void withdrawThrowsIfInsufficientFunds() {
        Account acc = new Account("1", "Alex", BigDecimal.valueOf(20));
        assertThrows(InsufficientFundsException.class,
                () -> acc.withdraw(BigDecimal.valueOf(50)));
    }

    @Test
    void negativeDepositThrows() {
        Account acc = new Account("1", "Alex", BigDecimal.valueOf(20));
        assertThrows(InvalidAmountException.class, () -> acc.deposit(BigDecimal.ZERO));
    }
}
