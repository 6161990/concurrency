package com.yoon.thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncrementInteger implements IncrementInteger{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public int get() {
        return atomicInteger.get();
    }

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }
}
