package com.yoon.thread.executors.pool;


import com.yoon.thread.executors.future.RunnableTask;

import java.util.concurrent.*;

import static com.yoon.thread.executors.ExecutorUtils.printState;
import static com.yoon.utils.LoggerLogger.log;

public class PoolSizeMainV4 {

    static final int TASK_SIZE = 1100; // 1. 일반
    //static final int TASK_SIZE = 1200; // 2. 긴급
    //static final int TASK_SIZE = 1201; // 3. 거절

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200, //core pool 100 + 100 , 그러니까 추가 스레드 100개만 생성된것
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000)); // 참고로 LinkedBlockedQueue 는 무한대라 큐가 가득찬 상황이 벌어지지않아 요청을 무한대로 받아버리니 주리
        printState(es);

        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }


        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));

    }
}
