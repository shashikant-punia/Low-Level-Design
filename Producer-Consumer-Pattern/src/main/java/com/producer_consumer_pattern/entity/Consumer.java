package com.producer_consumer_pattern.entity;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = queue.take();
                System.out.println(message + " consumed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
