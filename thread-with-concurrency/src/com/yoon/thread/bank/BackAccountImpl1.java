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
        log(getClass().getSimpleName() + " [거래 시작] balance=" + balance);

        if (balance < amount) {
            log("[거래 실패] amount=" + amount);
            return false;
        }

        sleep(1000);
        balance = balance - amount;
        log("[거래 및 출  완료] amount=" + amount + ", balance=" + balance);
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
