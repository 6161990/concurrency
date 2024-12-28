package com.yoon.thread.locks.lockSupport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

import static com.yoon.utils.LoggerLogger.log;

class CustomLock {
    private Thread owner; // 락 소유 스레드
    private final Queue<Thread> waitQueue = new ConcurrentLinkedQueue<>(); // 대기 중인 스레드 큐

    public boolean lock(long timeoutMillis) {
        Thread currentThread = Thread.currentThread();
        long deadline = System.nanoTime() + timeoutMillis * 1_000_000; // 타임아웃 계산

        while (!tryLockInternal()) {
            if (System.nanoTime() > deadline) { // 타임아웃 초과 시 락 획득 실패
                log("타임아웃 초과로 " + currentThread.getName() + "의 lock 획득 실패 ㅠㅠㅠㅠ 까비");
                waitQueue.remove(currentThread); // 대기 큐에서 제거
                return false; // 락 획득 실패
            }
            waitQueue.add(currentThread); // 현재 스레드를 대기 큐에 추가
            LockSupport.parkNanos(deadline - System.nanoTime()); // 남은 시간 동안 대기
        }
        waitQueue.remove(currentThread); // 락 획득 시 대기 큐에서 제거
        return true; // 락 획득 성공
    }

    public void unlock() {
        if (Thread.currentThread() == owner) {
            owner = null; // 락 소유권 반납
            Thread nextThread = waitQueue.poll(); // 대기 중인 다음 스레드
            if (nextThread != null) {
                LockSupport.unpark(nextThread); // 대기 중인 스레드를 깨움
            }
        }
    }

    private synchronized boolean tryLockInternal() {
        if (owner == null) {
            owner = Thread.currentThread();
            return true;
        }
        return false;
    }
}







