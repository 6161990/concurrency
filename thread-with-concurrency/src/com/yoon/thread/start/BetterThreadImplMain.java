package com.yoon.thread.start;

public class BetterThreadImplMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " start");
        BetterThreadImpl betterThread = new BetterThreadImpl();
        Thread thread = new Thread(betterThread);
        thread.start();

        System.out.println(Thread.currentThread().getName() + " end");
    }
}
