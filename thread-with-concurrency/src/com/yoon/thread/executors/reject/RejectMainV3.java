package com.yoon.thread.executors.reject;

import com.yoon.thread.executors.future.RunnableTask;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// CallerRunsPolicy :호출자가 대신 처리함 여기서는 main 스레드가 처리하게됨. 이렇게 되면 생산자는 원래 생산만 하는데 소비까지 처리해버리니까 생산자의 속도조절이 저절로 이루어지기도함
public class RejectMainV3 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());


        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));
        executor.submit(new RunnableTask("task4"));
        executor.close();
    }
}
