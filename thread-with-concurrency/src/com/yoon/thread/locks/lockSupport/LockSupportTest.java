package com.yoon.thread.locks.lockSupport;

import static com.yoon.utils.LoggerLogger.log;

public class LockSupportTest {

    public static void main(String[] args) {
        CustomLock customLock = new CustomLock();

        Thread yj = new Thread(getRunnable(customLock, 2000), "윤지");
        Thread bolt = new Thread(getRunnable(customLock, 1000), "우사인볼트");
        Thread sj = new Thread(getRunnable(customLock, 8000), "선재");

        // 스레드 시작
        yj.start();
        bolt.start();
        sj.start();
    }

    private static Runnable getRunnable(CustomLock customLock, int timeoutMillis) {
        return () -> {
            log(Thread.currentThread().getName() + "의 락 획득 시도");
            if (customLock.lock(timeoutMillis)) { // 락을 획득
                try {
                    log(Thread.currentThread().getName() + "의 락 획득 성공!!! 진행중 ");
                    Thread.sleep(2000); // 작업 수행
                } catch (InterruptedException e) {
                    log(Thread.currentThread().getName() + "Interrupted? " + Thread.currentThread().isInterrupted());
                } finally {
                    log(Thread.currentThread().getName() + "의 락 해제");
                    customLock.unlock(); // 락 반납
                }
            } else {
                log(Thread.currentThread().getName() + "의 락 획득 실패");
            }
        };
    }
}


