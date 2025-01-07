package com.yoon.thread.bounded;

public interface BoundedQueue {
    void put(String value);

    String take();
}
