package com.yoon.thread.locks.lockSupport;

import java.util.concurrent.locks.LockSupport;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

/**
 * java.util.concurrent 패키지 파헤치기 1
 * locks.LockSupport
 * 스레드를 WAITING 상태로 바꿔주는 기능 : sleep, join, wait 처럼!
 * 주요 기능 1.park 2.unpark -> 다시 RUNNABLE 상태로 전환하는 기능 : interrupt 처럼! but, isInterrupted 는 아님
 * */
public class LockSupportMain {
    public static void main(String[] args) {
        Thread parkThread = new Thread(new ParkThread(), "parkThread");
        parkThread.start();

        sleep(100);
        log("parkThread state : " + parkThread.getState());
//        log("---- parkThread unpark !");
//        LockSupport.unpark(parkThread);
        log("---- parkThread interrupt !");
        parkThread.interrupt();
    }

    static class ParkThread implements Runnable {

        @Override
        public void run() {
            log("start");
            log("state : " + Thread.currentThread().getState());
            log("---- parkThread park !");
            LockSupport.park();
//            log("park() 이후, 찍히나?"); // 안찍힘
            log("unpark() 이후, 찍히나?"); // 찍힘
            log("interrupt() 이후, 찍히나?"); // 찍힘
            log("state : " + Thread.currentThread().getState());
            log("isInterrupted : " + Thread.currentThread().isInterrupted());
        }
    }
}
