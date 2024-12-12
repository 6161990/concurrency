package com.yoon.thread.bank;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BackMain {
    public static void main(String[] args) throws InterruptedException {
//        BankAccount account = new BackAccountImpl1(1000);
//        BankAccount account = new BackAccountImpl2(1000);
        BankAccount account = new BackAccountImpl3(1000);
        log("BankAccount 가 여기서 공유자원임.");
        log("synchronized 를 사용하면 잘 돼.");
        log("synchronized 블록을 사용하면 약간의 성능이 나아질수도");

        Thread thread1 = new Thread(new WithdrawTask(account, 800), "thread1");
        Thread thread2 = new Thread(new WithdrawTask(account, 800), "thread2");
        log("공유자원을 각 스레드에서 접근할 때 동시성 이슈를 확인해볼거야");
        log("이건 단순 캐시 문제가 아니야. 메모리 가시성 문제를 뛰어넘는 이슈가 있어");
        log("volatile 로 공유자원을 세팅해도 문제는 동일하더라고");

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
