package com.yoon.thread.bank;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BackAccountImpl1 implements BankAccount {

    private int balance;

    public BackAccountImpl1(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        if (balance < amount) {
            log("거래 실패 balance=" + balance + ", amount=" + amount);
            return false;
        }

        log("검증완료 balance=" + balance + ", amount=" + amount);
        sleep(1000);
        balance = balance - amount;
        log("출금완료 balance=" + balance + ", amount=" + amount);
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
