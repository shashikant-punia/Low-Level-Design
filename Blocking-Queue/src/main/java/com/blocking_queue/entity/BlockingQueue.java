package com.blocking_queue.entity;

public interface BlockingQueue<T> {
    T take();

    void offer(T t);
}
