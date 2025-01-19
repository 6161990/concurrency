package com.yoon.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BoundedQueueImpl2 implements BoundedQueue{

    private final Queue<String> arrayDeque = new ArrayDeque<>();
    private final int max;

    public BoundedQueueImpl2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String value) {
        while (arrayDeque.size() == max){
            log("BoundedQueueImpl2's Queue full. waiting.. "+value);
            sleep(1000);
        }

        arrayDeque.offer(value);
    }

    @Override
    public synchronized String take() {
        while (arrayDeque.isEmpty()){
            log("BoundedQueueImpl2's Queue empty. waiting.. ");
            sleep(1000);
        }
        return arrayDeque.poll();
    }

    @Override
    public /*synchronized remove for test*/ String toString() {
        return arrayDeque.toString();
    }
}
