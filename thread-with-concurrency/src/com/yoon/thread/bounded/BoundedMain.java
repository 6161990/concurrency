package com.yoon.thread.bounded;

import java.util.ArrayList;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class BoundedMain {

    public static void main(String[] args) {
//        BoundedQueue queue = new BoundedQueueImpl1(2);
//        BoundedQueue queue = new BoundedQueueImpl2(2);
//        BoundedQueue queue = new BoundedQueueImpl3(2);
//        BoundedQueue queue = new BoundedQueueImpl4(2);
//        BlockedQueueImpl1 queue = new BlockedQueueImpl1(2);
//        BlockedQueueImpl2 queue = new BlockedQueueImpl2(2);
        BlockedQueueImpl4 queue = new BlockedQueueImpl4(2);

      producingFirst(queue);
//        consumerFirst(queue);

    }

    private static void consumerFirst(BoundedQueue queue) {
        log("consumerFirst " + queue.getClass().getSimpleName() + "-----");
        ArrayList<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(threads);
        startProducer(queue, threads);
        printAllState(threads);
    }

    private static void producingFirst(BoundedQueue queue) {
        log("producingFirst" + queue.getClass().getSimpleName() + "-----");
        ArrayList<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(threads);
        startConsumer(queue, threads);
        printAllState(threads);
    }

    private static void startProducer(BoundedQueue queue, ArrayList<Thread> producerContainers) {
        log("startProducer-----");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer"+i);
            producerContainers.add(producer);
            producer.start();
            sleep(100); // 로그 보기 편하게
        }

    }

    private static void printAllState(ArrayList<Thread> threads) {
        log("printAllState-----");
        for (Thread thread : threads) {
            log(thread.getName() + " : " + thread.getState());
        }
    }

    private static void startConsumer(BoundedQueue queue, ArrayList<Thread> consumerContainers) {
        log("startConsumer-----");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer"+i);
            consumerContainers.add(consumer);
            consumer.start();
            sleep(100); // 로그 보기 편하게
        }
    }


}
