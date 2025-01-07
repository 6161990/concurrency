package com.yoon.thread.bounded;

import static com.yoon.utils.LoggerLogger.log;

public class ConsumerTask implements Runnable {

    private final BoundedQueue boundedQueue;

    public ConsumerTask(BoundedQueue boundedQueue) {
        this.boundedQueue = boundedQueue;
    }

    @Override
    public void run() {
        log("ConsumerTask before run boundedQueue" + boundedQueue);
        boundedQueue.take();
        log("ConsumerTask after run boundedQueue" + boundedQueue);
    }
}
