package com.example.bank;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    @Test
    void transferMovesMoneyBetweenAccounts() {
        BankService bank = new BankService();
        bank.createAccount("A", "A", BigDecimal.valueOf(100));
        bank.createAccount("B", "B", BigDecimal.valueOf(10));
        bank.transfer("A", "B", BigDecimal.valueOf(25));
        assertEquals(BigDecimal.valueOf(75), bank.getAccount("A").getBalance());
        assertEquals(BigDecimal.valueOf(35), bank.getAccount("B").getBalance());
    }

    @Test
    void transferFromUnknownAccountThrows() {
        BankService bank = new BankService();
        bank.createAccount("B", "B", BigDecimal.valueOf(10));
        assertThrows(AccountNotFoundException.class,
                () -> bank.transfer("A", "B", BigDecimal.valueOf(5)));
    }

    @Test
    void transferWithNonPositiveAmountThrows() {
        BankService bank = new BankService();
        bank.createAccount("A", "A", BigDecimal.valueOf(100));
        bank.createAccount("B", "B", BigDecimal.valueOf(100));
        assertThrows(InvalidAmountException.class,
                () -> bank.transfer("A", "B", BigDecimal.ZERO));
    }
}
