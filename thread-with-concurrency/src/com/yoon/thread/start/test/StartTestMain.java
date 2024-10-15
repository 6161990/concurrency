package com.yoon.thread.start.test;

import static com.yoon.utils.LoggerLogger.log;

public class StartTestMain {

    public static void main(String[] args) {
        // 1
        StartTest startTest = new StartTest();
        startTest.start();

        // 2
        CounterRunnable counterRunnable = new CounterRunnable();
        Thread thread = new Thread(counterRunnable, "counter");
        thread.start();

        // 3
        Thread threadA = new Thread(new PrintingThreadName("A", 1000), "Thread-A");
        threadA.start();

        Thread threadB = new Thread(new PrintingThreadName("B", 500), "Thread-B");
        threadB.start();
    }

    static class PrintingThreadName implements Runnable {
        private final String logMessage;
        private final int sleepCount;

        public PrintingThreadName(String logMessage, int sleepCount) {
            this.logMessage = logMessage;
            this.sleepCount = sleepCount;
        }

        @Override
        public void run() {
            while (true){
                log(logMessage);
                try {
                    Thread.sleep(sleepCount);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class StartTest extends Thread {
        @Override
        public void run() {
            countingAndLogging();
        }
    }

    static class CounterRunnable implements Runnable {

        @Override
        public void run() {
            countingAndLogging();
        }
    }

    private static void countingAndLogging() {
        for (int i = 1; i < 6; i++) {
            log("value : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
