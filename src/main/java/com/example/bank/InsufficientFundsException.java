package com.example.bank;

/** Thrown when a withdrawal exceeds the current balance. */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) { super(message); }
}
