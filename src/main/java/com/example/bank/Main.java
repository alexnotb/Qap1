
package com.example.bank;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

/**
 * Minimal CLI demo for the Simple Banking Application.
 * Not required for tests/CI, but useful for manual runs.
 */
public class Main {
    public static void main(String[] args) {
        BankService bank = new BankService();
        Account acc = bank.createAccount("Demo User", new BigDecimal("100.00"));

        System.out.println("== Simple Banking App Demo ==");
        System.out.println("Account holder: " + acc.getHolderName() + ", balance: " + acc.getBalance());
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        while (true) {
            System.out.print("\nChoose: (1) balance  (2) deposit  (3) withdraw  (4) exit: ");
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        System.out.println("Balance: " + acc.getBalance());
                        break;
                    case "2":
                        System.out.print("Amount to deposit: ");
                        BigDecimal d = new BigDecimal(in.nextLine().trim());
                        bank.deposit(acc, d);
                        System.out.println("Deposited. New balance: " + acc.getBalance());
                        break;
                    case "3":
                        System.out.print("Amount to withdraw: ");
                        BigDecimal w = new BigDecimal(in.nextLine().trim());
                        bank.withdraw(acc, w);
                        System.out.println("Withdrawn. New balance: " + acc.getBalance());
                        break;
                    case "4":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
