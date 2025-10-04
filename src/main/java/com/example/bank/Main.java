package com.example.bank;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BankService bank = new BankService();
        bank.createAccount("A1", "Oleksii", BigDecimal.valueOf(100));
        bank.createAccount("A2", "Friend", BigDecimal.valueOf(50));
        bank.transfer("A1", "A2", BigDecimal.valueOf(20));
        System.out.println("A1 balance: " + bank.getAccount("A1").getBalance());
        System.out.println("A2 balance: " + bank.getAccount("A2").getBalance());
    }
}
