package com.yoon.thread.bank;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BackMain {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BackAccountImpl1(1000);
        log("BankAccount 가 여기서 공유자원임.");

        Thread thread1 = new Thread(new WithdrawTask(account, 800), "thread1");
        Thread thread2 = new Thread(new WithdrawTask(account, 800), "thread2");
        log("공유자원을 각 스레드에서 접근할 때 동시성 이슈를 확인해볼거야");

        thread1.start();
        thread2.start();

        sleep(500);
        log("thread1" + thread1.getState());
        log("thread2" + thread2.getState());


        thread1.join();
        thread2.join();

        log("[최종 잔액] balance=" + account.getBalance());
    }
}
