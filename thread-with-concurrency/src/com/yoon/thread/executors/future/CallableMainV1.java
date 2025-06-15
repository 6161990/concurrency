package com.yoon.thread.executors.future;

import com.yoon.thread.executors.ExecutorUtils;

import java.util.Random;
import java.util.concurrent.*;

import static com.yoon.utils.LoggerLogger.log;

public class CallableMainV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> submit = es.submit(new CallableTask());
        ExecutorUtils.printState(es);
        Integer randomInteger = submit.get();
        log("random=" + randomInteger);

        es.shutdown();
        ExecutorUtils.printState(es);
    }

    public static class CallableTask implements Callable<Integer> {

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
