package com.yoon.thread.state;

import static com.yoon.utils.LoggerLogger.log;
import static java.lang.Thread.sleep;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadStateImpl(), "ThreadStateImpl");
        log("MainThread's state " + Thread.currentThread().getState()); // main RUNNABLE
        thread.start();
        sleep(1000);
        log("ThreadStateImpl's state " + thread.getState()); // TIMED_WAITING
        sleep(4000);
        log("ThreadStateImpl's state " + thread.getState()); // TERMINATED
        log("MainThread's state " + Thread.currentThread().getState()); // main RUNNABLE
    }

    static class ThreadStateImpl implements Runnable {

        @Override
        public void run() {
            try {
                log("ThreadStateImpl's start");
                log("ThreadStateImpl's sleep before : state " + Thread.currentThread().getState()); // RUNNABLE
                sleep(3000);
                log("ThreadStateImpl's sleep after : state " + Thread.currentThread().getState()); // RUNNABLE
                log("ThreadStateImpl's end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
