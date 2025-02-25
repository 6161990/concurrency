package com.yoon.thread.cas.increment;

public class SynchronizedIncrementInteger implements IncrementInteger {

    private int i;

    @Override
    public synchronized int get() {
        return i;
    }

    @Override
    public synchronized void increment() {
        i++;
    }
}
