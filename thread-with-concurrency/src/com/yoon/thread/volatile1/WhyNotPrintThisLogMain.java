package com.yoon.thread.volatile1;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class WhyNotPrintThisLogMain {

    public static void main(String[] args) {
        WhyNotPrintThisLogTask task = new WhyNotPrintThisLogTask();
        Thread thread = new Thread(task);
        thread.start();

        sleep(1000);
        log("WhyNotPrintThisLogTask's state = " + task.trigger);
        task.trigger = false;
        log("WhyNotPrintThisLogTask's state = " + task.trigger);
        log("메인 종료");
    }

    static class WhyNotPrintThisLogTask implements Runnable {

        boolean trigger = true;

        @Override
        public void run() {
            log("WhyNotPrintThisLogTask start");
            while (trigger) {

            }
            log("WhyNotPrintThisLog...??????");
        }
    }
}
