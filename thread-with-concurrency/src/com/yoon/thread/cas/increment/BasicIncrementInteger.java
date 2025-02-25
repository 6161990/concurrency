package com.yoon.thread.cas.increment;

public class BasicIncrementInteger implements IncrementInteger{

    private int i;


    @Override
    public int get() {
        return i;
    }

    @Override
    public void increment() {
        i++;
    }
}
