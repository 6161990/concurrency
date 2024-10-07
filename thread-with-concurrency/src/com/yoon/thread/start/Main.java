package com.yoon.thread.start;

public class Main {
    public static void main(String[] args) {
        System.out.println("🤢🤢🤢🤢🤢🤢🤢🤢🤢");
        TwiceThread TT = new TwiceThread();
        System.out.println("Current Thread is : "  + Thread.currentThread().getName());
        System.out.println("TT before start Current : "  + Thread.currentThread().getName());
        TT.start();
        System.out.println("TT after start Current : "  + Thread.currentThread().getName());

        System.out.println("Current Thread is : "  + Thread.currentThread().getName());
        System.out.println("🤢🤢🤢🤢🤢🤢🤢🤢🤢");
    }
}
