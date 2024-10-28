package com.yoon.thread.interrupt;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class WhyNeedInterruptMain {

    public static void main(String[] args) {
        NeedInterrupt needInterrupt = new NeedInterrupt();
        Thread thread = new Thread(needInterrupt, "needInterrupt");

        log("start working ~ ");
        thread.start();
        sleep(4000);
        log("would please stop working now?");
        needInterrupt.runFlag = false;

    }

    static class NeedInterrupt implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {

            while (runFlag) {
                sleep(3000);
                log("working...");
            }

            log("end working");
        }
    }
}
