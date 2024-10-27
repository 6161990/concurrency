package com.yoon.thread.join;

import com.yoon.utils.LoggerLogger;

public class WhyNeedJoinMain {

    public static void main(String[] args) {
        LoggerLogger.log("main start.");

        SumValue task1 = new SumValue(1, 50);
        SumValue task2 = new SumValue(51, 100);

        Thread thread1 = new Thread(task1, "thread1");
        Thread thread2 = new Thread(task2, "thread2");

        thread1.start();
        thread2.start();

        LoggerLogger.log("task1 start. sum = "+ task1.sumValue);
        LoggerLogger.log("task2 start. sum = "+ task2.sumValue);

        int sum = task1.sumValue + task2.sumValue;

        LoggerLogger.log("main end. sum = " + sum);
    }

    static class SumValue implements Runnable {
        int startValue;
        int endValue;
        int sumValue;

        public SumValue(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            LoggerLogger.log("Sum start");
            try {
                for (int i = startValue; i <= endValue; i++) {
                    sumValue += i;
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LoggerLogger.log("Sum ending. sum is " + sumValue);
        }
    }
}
