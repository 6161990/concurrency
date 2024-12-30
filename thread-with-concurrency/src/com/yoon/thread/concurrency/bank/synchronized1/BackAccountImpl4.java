package com.yoon.thread.concurrency.bank.synchronized1;

import com.yoon.thread.concurrency.bank.BankAccount;

import java.util.concurrent.locks.ReentrantLock;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BackAccountImpl4 implements BankAccount {

    private int balance;

    public BackAccountImpl4(int initialBalance) {
        this.balance = initialBalance;
    }

    private final ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public boolean withdraw(int amount) {
        log(getClass().getSimpleName() + " [거래 시작] balance=" + balance);

        reentrantLock.lock();
        try {
            if (balance < amount) {
                log("[거래 실패] amount=" + amount);
                return false;
            }
            sleep(1000);
            balance = balance - amount;
        }finally {
            reentrantLock.unlock();
            log("[거래 및 출금 완료] amount=" + amount + ", balance=" + balance);
        }

        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
