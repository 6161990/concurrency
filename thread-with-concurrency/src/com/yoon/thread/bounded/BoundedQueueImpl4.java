package com.yoon.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.yoon.utils.LoggerLogger.log;

/**
 * 모니터 락이 아니라  ReentrantLock 을 사용하여 대기실(Condition)을 만든 다음,
 * 그곳에서 공유자원에 대한 소비, 생산을 처리할 수 있도록한다 .
 *
* */
public class BoundedQueueImpl4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition produceCondition = lock.newCondition();
    private final Condition consumeCondition = lock.newCondition();

    private final Queue<String> arrayDeque = new ArrayDeque<>();
    private final int max;

    public BoundedQueueImpl4(int max) {
        this.max = max;
    }

    @Override
    public void put(String value) {
        lock.lock();
        try {
            while (arrayDeque.size() == max) {
                log("BoundedQueueImpl4's Queue full. waiting.. " + value);
                try {
                    produceCondition.await();
                    log("생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            arrayDeque.offer(value);
            consumeCondition.signal();
            log("생산자 알림");
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (arrayDeque.isEmpty()) {
                log("BoundedQueueImpl4's Queue empty. waiting.. ");
                try {
                    consumeCondition.await();
                    log("소비자 대기");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String poll = arrayDeque.poll();
            produceCondition.signal();
            log("소비자 알림");
            return poll;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return arrayDeque.toString();
    }
}
