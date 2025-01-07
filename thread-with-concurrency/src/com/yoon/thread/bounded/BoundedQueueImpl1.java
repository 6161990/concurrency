package com.yoon.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.yoon.utils.LoggerLogger.log;

public class BoundedQueueImpl1 implements BoundedQueue{

    private final Queue<String> arrayDeque = new ArrayDeque<>();
    private final int max;

    public BoundedQueueImpl1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String value) {
        if(arrayDeque.size() == max){
            log("BoundedQueueImpl1's Queue full. remove data : "+value);
            return;
        }

        arrayDeque.offer(value);
    }

    @Override
    public synchronized String take() {
        if(arrayDeque.isEmpty()){
            return null;
        }
        return arrayDeque.poll();
    }

    @Override
    public /*synchronized remove for test*/ String toString() {
        return arrayDeque.toString();
    }
}
