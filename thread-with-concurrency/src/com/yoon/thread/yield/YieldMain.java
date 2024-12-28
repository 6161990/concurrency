package com.yoon.thread.yield;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

/**
 * Yield
 * 어떤 스레드가 다른 스레드 먼저 실행하게끔 양보하는 역할
 * 양보는 보장되지않는다. 완전 완벽으로 동작되는 것은 아님. CPU 에게 힌트만 주는 느낌인 것 같다.
 * 그래도 상태는 RUNNABLE(READY --> 스케쥴링 큐에서 대기중)

 * sleep 도 비슷하게 흘러가긴한데, TIME_WAITING 상태로 변경된다.
 * */
public class YieldMain {

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new NeedYield());
            thread.start();
        }
    }

    static class NeedYield implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                log(Thread.currentThread().getName() + "'s current int - " + i);
                // 1. empty
//                 sleep(1); // 2.
//                 Thread.yield(); // 3.
            }
        }
    }
}
