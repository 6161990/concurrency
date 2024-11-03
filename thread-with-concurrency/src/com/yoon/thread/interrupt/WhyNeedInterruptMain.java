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
        thread.interrupt();  // interrupt 호출 시 바로 예외가 발생하지 않고 sleep, wait, join 등의 메서드가 실행 중일 때 InterruptedException 발생
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
                log("isInterrupted? " + Thread.currentThread().isInterrupted()); // Interrupted 에 의해 InterruptedException이 발생
                log("인터럽트 상태에서 인터럽트 예외가 발생하면 work 스레드는 다시 동작하는 상태가되면서 interrupted 종료됨");
                log("exception message is " + e.getMessage()); // RUNNABLE
                log("now state? " + Thread.currentThread().getState()); // InterruptedException이 발생하면 interrupted 상태는 해제됨 (false)
            }
        }
    }
}
