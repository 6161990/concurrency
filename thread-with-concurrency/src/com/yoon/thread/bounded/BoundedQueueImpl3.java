package com.yoon.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

/**
 * 문제점1) 동일 생산, 동일 소비하며 비효율적으로 동작할 수 있음
 * 문제점2) 스레드 기아현 - notifyAll 로 해결가능하긴함.
 * */
public class BoundedQueueImpl3 implements BoundedQueue{

    private final Queue<String> arrayDeque = new ArrayDeque<>();
    private final int max;

    public BoundedQueueImpl3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String value) {
        while (arrayDeque.size() == max){
            log("BoundedQueueImpl3's Queue full. waiting.. "+value);
            try {
                wait(); // RUNNABLE -> WAITING + 락 반납
                log("생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        arrayDeque.offer(value);
        notify(); // 대기 스레드에게 알림. WAITING -> RUNNABLE
        log("생산자 알림");
    }

    @Override
    public synchronized String take() {
        while (arrayDeque.isEmpty()){
            log("BoundedQueueImpl3's Queue empty. waiting.. ");
            try {
                wait(); // RUNNABLE -> WAITING + 락 반납
                log("소비자 대기");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String poll = arrayDeque.poll();
        notify();
        log("소비자 알림");
        return poll;
    }

    @Override
    public /*synchronized remove for test*/ String toString() {
        return arrayDeque.toString();
    }
}
