package com.yoon.thread.concurrency.bank;

public interface BankAccount {

    boolean withdraw(int amount);

    int getBalance();

}
