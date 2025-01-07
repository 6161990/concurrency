package com.yoon.thread.bounded;

import static com.yoon.utils.LoggerLogger.log;

public class ProducerTask implements Runnable {

    private final BoundedQueue boundedQueue;
    private final String request;

    public ProducerTask(BoundedQueue boundedQueue, String request) {
        this.boundedQueue = boundedQueue;
        this.request = request;
    }

    @Override
    public void run() {
        log("ProducerTask before run request" + request + " ->>" + boundedQueue);
        boundedQueue.put(request);
        log("ProducerTask after run boundedQueue " + boundedQueue);
    }
}
