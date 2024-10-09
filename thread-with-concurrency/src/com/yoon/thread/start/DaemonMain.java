package com.yoon.thread.start;

public class DaemonMain {

    public static void main(String[] args) {
        System.out.println("MainThread start :" + Thread.currentThread().getName());
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println("MainThread end :" + Thread.currentThread().getName());
    }

    static class DaemonThread extends Thread {

        @Override
        public void run() {
            System.out.println("DaemonThread start :" + Thread.currentThread().getName());
            System.out.println("Are U Daemon ? " + Thread.currentThread().isDaemon());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("DaemonThread end :" + Thread.currentThread().getName());
        }
    }

}
