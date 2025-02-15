package com.yoon.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockedQueueImpl implements BoundedQueue {

    private final BlockingQueue<String> blockingQueue;

    public BlockedQueueImpl(int max) {
        this.blockingQueue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String value) {
        try {
            blockingQueue.put(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return blockingQueue.toString();
    }
}
