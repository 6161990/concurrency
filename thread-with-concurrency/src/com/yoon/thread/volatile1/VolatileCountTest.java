package com.yoon.thread.volatile1;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class VolatileCountTest {

    public static void main(String[] args) {
        VolatileCountTask task = new VolatileCountTask();
        Thread thread = new Thread(task);
        thread.start();

        sleep(1000);
        log("VolatileCountTask's state = " + task.trigger);
        task.trigger = false;
        log("trigger = "+ task.trigger + ", " + "count = " + task.count + " in main()");
        log("아마 여기 main 스레드에서 false 를 반영한 시점과 VolatileCountTask 가 false 를 확인한 시점이 다를거야.");
        log("23 번 라인에 변수가 volatile 있냐없냐에 따라서 count 확인 시점이 서로 달라지겠지 ");
    }

    static class VolatileCountTask implements Runnable {

        boolean trigger = true;
        int count;

        @Override
        public void run() {
            log("VolatileCountTask start");
            while (trigger) {
                count ++;
                if(count % 100_000_000 == 0){
                    log("trigger = "+ trigger + ", " + "count = " + count + " in while()"); // 여기서 이걸 출력할 때 스레드 대기 상태가 되면서 변수의 변경을 알아차리게됨
                }
            }
            log("trigger = "+ trigger + ", " + "count = " + count + " 종료");
        }
    }
}
