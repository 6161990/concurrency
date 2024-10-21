package com.yoon.utils;

import static com.yoon.utils.LoggerLogger.log;

public abstract class ThreadSleepUtils {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log("InterruptedException " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
