package com.yoon.thread.locks.lockSupport;

import java.util.concurrent.locks.LockSupport;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

/**
 * java.util.concurrent 패키지 파헤치기 2
 * locks.LockSupport
 * LockSupport.parkNanos(2000_000000) : 2초 뒤에 알아서 깨어남
 * */
public class LockSupportMain2 {
    public static void main(String[] args) {
        Thread parkThread = new Thread(new ParkThread(), "parkThread");
        parkThread.start();

        sleep(100);
        log("parkThread state : " + parkThread.getState());
    }

    static class ParkThread implements Runnable {

        @Override
        public void run() {
            log("start");
            log("state : " + Thread.currentThread().getState());
            log("---- parkThread parkNanos !");
            LockSupport.parkNanos(2000_000000);
            log("state : " + Thread.currentThread().getState());
        }
    }
}
