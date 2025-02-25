package com.yoon.thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static com.yoon.utils.ThreadSleepUtils.sleep;

public class IncrementIntegerMain {

    private static final int THREAD_HOLDS = 1000;

    public static void main(String[] args) {
        BasicIncrementInteger basicIncrementInteger = new BasicIncrementInteger();
        test(basicIncrementInteger);
    }

    private static void test(IncrementInteger incrementInteger) {
        Runnable runnable = () -> {
            sleep(10);
            incrementInteger.increment();
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_HOLDS; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result : " + result);
    }
}
