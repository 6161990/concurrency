package com.yoon.thread.yield;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

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
