package com.producer_consumer_pattern;

import com.producer_consumer_pattern.entity.Consumer;
import com.producer_consumer_pattern.entity.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerPattern {
    public static void main(String[] args) {
        BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(10);

        Producer producer1 = new Producer(blockingDeque);
        Producer producer2 = new Producer(blockingDeque);

        Consumer consumer1 = new Consumer(blockingDeque);
        Consumer consumer2 = new Consumer(blockingDeque);

        new Thread(producer1).start();
        new Thread(producer2).start();

        new Thread(consumer1).start();
        new Thread(consumer2).start();

    }
}