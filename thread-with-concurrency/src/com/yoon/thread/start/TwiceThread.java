package com.yoon.thread.start;

public class TwiceThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
