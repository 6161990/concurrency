package com.yoon.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.yoon.utils.LoggerLogger.log;

public class SpinLockWithCas {
    private volatile AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("try lock");
        while (!lock.compareAndSet(false, true)) {
            log("try lock failed. spin waiting");
        }
        log("try lock success.");
    }

    public void unlock(){
        lock.set(false);
        log("unlock 완료");
    }
}
