package com.yoon.thread.volatile1;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;
/**
 * 캐시 메모리를 사용하면 CPU 처리 성능을 개선할 수 있다.
 * 하지만 때로는 성능 향상보다는 여러 스레드에서 같은 시점에 정확히 같은 데이터를 보는 것이 더 중요할 수 있다.
 * 값을 읽을 때, 값을 쓸 때 모두 메인 메모리에 직접 접근하면 된다.
 * 자바에서 그 지원을 하는 키워드가 바로 volatile
 * */
public class VolatileMain {

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

        volatile boolean trigger = true;

        @Override
        public void run() {
            log("WhyNotPrintThisLogTask start");
            while (trigger) {

            }
            log("Finally printing this log!");
            log("trigger 변수에 volatile 키워드를 사용하면 값을 읽을 때, 값을 쓸 때 모두 메인 메모리에 직접 접근함");
        }
    }
}
