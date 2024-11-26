package com.yoon.thread.join;

import com.yoon.utils.LoggerLogger;

import static com.yoon.utils.ThreadSleepUtils.sleep;

public class WhyNeedJoinMain {

    public static void main(String[] args) throws InterruptedException {
        LoggerLogger.log("main start.");

        SumValue task1 = new SumValue(1, 50);
        SumValue task2 = new SumValue(51, 100);

        Thread thread1 = new Thread(task1, "thread1");
        Thread thread2 = new Thread(task2, "thread2");

        thread1.start();
        thread2.start();

        thread1.join(3000); // TIMED_WAITING
        LoggerLogger.log("task1's " + task1.sumValue + ", state=" + thread1.getState());
        thread2.join(); // WAITING
        LoggerLogger.log("task2's " + task2.sumValue + ", state=" + thread2.getState());

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
            sleep(2000);
            for (int i = startValue; i <= endValue; i++) {
                sumValue += i;
            }
            LoggerLogger.log(Thread.currentThread().getName() + ", state=" + Thread.currentThread().getState());
        }
    }
}
