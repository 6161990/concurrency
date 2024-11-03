package com.yoon.thread.interrupt;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class UseInterruptMain {

    public static void main(String[] args) {
        WhyUseInterrupted whyUseInterrupted = new WhyUseInterrupted();
        Thread thread = new Thread(whyUseInterrupted, "whyUseInterrupted");

        log("start working ~ ");
        thread.start();
        sleep(100);
        log("interrupt working --------- 작업 중단 지시 ");
        thread.interrupt();
        log("isInterrupted?" + thread.isInterrupted());
    }

    static class WhyUseInterrupted implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) { // 인터럽트 상태라면 true 를 반환하고 인터럽트 상태를 false 로 변경한다.
                log("working...");
            }
            log("isInterrupted? " + Thread.currentThread().isInterrupted());

            try {
                log("clean resource start...");
                Thread.sleep(1000);
                log("clean resource end...");
            } catch (InterruptedException e) {
                log("자원 정리중 InterruptedException 발생 ");
                log("isInterrupted? " + Thread.currentThread().isInterrupted());
                log("exception message is " + e.getMessage());
                log("now state? " + Thread.currentThread().getState());
            }
        }
    }
}
