package com.yoon.thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.yoon.utils.LoggerLogger.log;

public class BlockedQueueImpl4 implements BoundedQueue {

    private final BlockingQueue<String> blockingQueue;

    public BlockedQueueImpl4(int max) {
        this.blockingQueue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String value) {
        try {
            blockingQueue.add(value); // IllegalStateException
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            return blockingQueue.remove(); // IllegalStateException
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return blockingQueue.toString();
    }
}
