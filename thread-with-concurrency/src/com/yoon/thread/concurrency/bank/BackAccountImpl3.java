package com.yoon.thread.concurrency.bank;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BackAccountImpl3 implements BankAccount {

    private int balance;

    public BackAccountImpl3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log(getClass().getSimpleName() + " [거래 시작] balance=" + balance);

        synchronized (this) {
            if (balance < amount) {
                log("[거래 실패] amount=" + amount);
                return false;
            }
            sleep(1000);
            balance = balance - amount;
        }

        log("[거래 및 출금 완료] amount=" + amount + ", balance=" + balance);
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
