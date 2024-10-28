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
                log("now state? " + Thread.currentThread().getState());
                log("isInterrupted? " + Thread.currentThread().isInterrupted());
            }

            try {
                log("자원정리 시작");
                Thread.sleep(50); // interrupt 상태를 제대로 정리하지 않으면 여기서 InterruptedException 이 발생해서 하위 코드에 영향을 줌
                log("자원정리 끝");
            } catch (InterruptedException e) {
                log("InterruptedException 자원정리 실패");
                log("isInterrupted? " + Thread.currentThread().isInterrupted());
            }
        }
    }
}
