package com.yoon.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.yoon.utils.LoggerLogger.log;

public class BlockedQueueImpl3 implements BoundedQueue {

    private final BlockingQueue<String> blockingQueue;

    public BlockedQueueImpl3(int max) {
        this.blockingQueue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String value) {
        try {
            boolean offer = blockingQueue.offer(value, 1, TimeUnit.NANOSECONDS);
            log("데이터 추가 결과 = " + offer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            return blockingQueue.poll(2, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return blockingQueue.toString();
    }
}
