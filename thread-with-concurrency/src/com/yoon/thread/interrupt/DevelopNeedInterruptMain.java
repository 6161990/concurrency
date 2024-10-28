package com.yoon.thread.interrupt;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class DevelopNeedInterruptMain {

    public static void main(String[] args) {
        DevelopNeedInterrupt developNeedInterrupt = new DevelopNeedInterrupt();
        Thread thread = new Thread(developNeedInterrupt, "needInterrupt");

        log("start working ~ ");
        thread.start();
        sleep(30);
        log("interrupt working --------- 작업 중단 지시 ");
        thread.interrupt();
        log("isInterrupted? " + thread.isInterrupted());
    }

    static class DevelopNeedInterrupt implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                log("working...");
                log("isInterrupted? " + Thread.currentThread().isInterrupted());
                log("now state? " + Thread.currentThread().getState());
            }
        }
    }
}
