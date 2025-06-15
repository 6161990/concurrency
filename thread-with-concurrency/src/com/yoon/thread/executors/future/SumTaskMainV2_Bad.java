package com.yoon.thread.executors.future;

import java.util.concurrent.*;

import static com.yoon.utils.LoggerLogger.log;

public class SumTaskMainV2_Bad {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(task1);
        Integer sum1 = future1.get(); // BAD !!!

        Future<Integer> future2 = es.submit(task2);
        Integer sum2 = future2.get();

        /**
         * FIXME 15 ~ 19 line
         * 이렇게 되면 ex.submit 을 이용해 future 객체와 future.get 을 분리한 이유가 없다.
         * main 스레드는 future1.get 을 통해 join (값을 가져올 때 까지 기다리는 block) 2초 기다림.
         * 그동안 future2 도 이미 수행이 끝나있어서 future2 가 값을 응답할 때까지 기다릴 필요가 없음.
         *        Future<Integer> future1 = es.submit(task1);
         *        Future<Integer> future2 = es.submit(task2);
         *
         *         Integer sum1 = future1.get();
         *         Integer sum2 = future2.get();
         *
         * 근데 15 ~ 19 line 처럼 코드 짜놓으면
         * future1 에서 2초 기다린 다음에 future2 에서 또 2초 기다림. 총 4초
         * 왜냐면 main 스레드는 future1.get() 에서 2초 기다리는데,
         * block되는 동안 es.submit(task2) 는 수행안될거기때문에, 4초가 걸리는것임.
         *
         * 추가로, 아래 코드도 마찬가지 총 4초 걸리게 됨.
         *         Integer sum1 = es.submit(task1).get();
         *         Integer sum2 = es.submit(task2).get();
         *
         *  :: 정리 ::
         *  future 는 요청 스레드를 블로킹 상태로 만들지 않고, 모든 요청을 "일단" 수행할 수 있게 해준다.
         *  필요한 요청을 한 다음에, get 을 통해 기다리면된다.
         * */

        log("task1.result=" + sum1);
        log("task2.result=" + sum2);

        int sumAll = sum1 + sum2;
        log("task1 + task2 = " + sumAll);
        log("End");

        es.shutdown();
    }

    static class SumTask implements Callable<Integer> {
        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws InterruptedException {
            log("작업 시작");
            Thread.sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            log("작업 완료 result=" + sum);
            return sum;
        }
    }
}
