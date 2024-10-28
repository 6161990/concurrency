package com.yoon.thread.interrupt;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class WhyNeedInterruptMain {

    public static void main(String[] args) {
        NeedInterrupt needInterrupt = new NeedInterrupt();
        Thread thread = new Thread(needInterrupt, "needInterrupt");

        log("start working ~ ");
        thread.start();
        sleep(4000);
        log("interrupt working --------- 작업 중단 지시 ");
        thread.interrupt(); // 바로 예외가 터지는 건 아니고 내부에서 Join or Sleep 메소드처럼 InterruptedException 을 핸들링하고 있으면 예외 발생함
        log("isInterrupted?" + thread.isInterrupted());
    }

    static class NeedInterrupt implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("working...");
                    Thread.sleep(3000); // TIMED_WAITING -> interrupt 에 의해 인터럽트 상태가 되었고 예외 발생
                }
            } catch (InterruptedException e) {
                log("isInterrupted? " + Thread.currentThread().isInterrupted()); // Interrupted 에 의해 예외가 발생하면 더이상 Interrupted 가 아니게 됨 false
                log("인터럽트 상태에서 인터럽트 예외가 발생하면 work 스레드는 다시 동작하는 상태가되면서 interrupted 종료됨"); // Interrupted 에 의해 예외가 발생하면 더이상 Interrupted 가 아니게 됨 false
                log("exception message is " + e.getMessage()); // RUNNABLE
                log("now state? " + Thread.currentThread().getState());
            }
        }
    }
}
