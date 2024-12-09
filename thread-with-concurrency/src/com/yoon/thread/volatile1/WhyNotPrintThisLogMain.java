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
            log("because of memory volatile");
            log("trigger 변수는 메인 메모리에 값이 저장된 상황" +
                    "요즘은 CPU 코어당 캐시 메모리가 붙음" +
                    "메인 스레드 캐시메모리, 테스크 스레드 캐시메모리에 trigger 값이 각각 캐싱된다" +
                    "trigger 의 값이 변경되어도 메인 스레드 캐시메모리에만 변경이 되는 것." +
                    "근데 시간이 흘러서 메인 메모리에 값이 변경될수도 (영원히 안 될수도)" +
                    "메인 메모리에 값이 변경되어도 테스크 스레드 캐시 메모리에 반영이 언제 될지도 역시 모름.."+
                    "주로 컨텍스트 스위칭할 때 메모리에 반영됨" +
                    "한 스레드에서 변경한 값이 다른 스레드에 보이냐 안보이냐의 문제 = 메모리 가시성 문제");
        }
    }
}
