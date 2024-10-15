package com.yoon.thread.start;

public class BetterThreadImpl implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " BetterThreadImpl's run");
    }
}
