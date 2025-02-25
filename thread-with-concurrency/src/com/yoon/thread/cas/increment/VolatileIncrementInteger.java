package com.yoon.thread.cas.increment;

public class VolatileIncrementInteger implements IncrementInteger{

    volatile private int i;

    @Override
    public int get() {
        return i;
    }

    @Override
    public void increment() {
        i++;
    }
}
