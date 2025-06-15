package com.yoon.thread.executors.future;

import com.yoon.thread.executors.ExecutorUtils;

import java.util.Random;
import java.util.concurrent.*;

import static com.yoon.utils.LoggerLogger.log;

public class CallableMainV1WithLogging {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new YoonJiRandomGenerateCallableTask());
        log("Future 즉시 확인하기 ==> " + future);
        log("check logging  Not completed, YoonJiRandomGenerateCallableTask");
        log("----- [blocking start] get checking start ----- main thread WAITING ");
        Integer getFuture = future.get();
        log("Future get 으로 확인하기 ==> " + getFuture);
        log("Future 상태 다시 확인하기 ==>  future.isDone? " + future.isDone());
        log("----- [blocking end] get checking end ----- main thread RUNNABLE ");

        es.shutdown();
        ExecutorUtils.printState(es);
    }

    static class YoonJiRandomGenerateCallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable task start");
            int random = new Random().nextInt(10);
            log("Callable task random=" + random);
            log("Callable task end");
            return random;
        }
    }
}
