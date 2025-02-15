package com.yoon.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.yoon.utils.LoggerLogger.log;

public class BlockedQueueImpl2 implements BoundedQueue {

    private final BlockingQueue<String> blockingQueue;

    public BlockedQueueImpl2(int max) {
        this.blockingQueue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String value) {
        boolean offer = blockingQueue.offer(value);
        log("데이터 추가 결과 = " + offer);
    }

    @Override
    public String take() {
        return blockingQueue.poll();
    }

    @Override
    public String toString() {
        return blockingQueue.toString();
    }
}
