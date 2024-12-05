package com.producer_consumer_pattern.entity;

import java.util.Queue;

public class ProducerRaw implements Runnable {
    private final int capacity;
    private final Queue<String> tasks;

    public ProducerRaw(int capacity, Queue<String> tasks) {
        this.capacity = capacity;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        synchronized (tasks) {
            while (true) {
                while (tasks.size() >= capacity) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Waiting for queue to drain");
                        tasks.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                String message = String.valueOf(System.currentTimeMillis());
                System.out.println(Thread.currentThread().getName() + " produced message: " + message);

                tasks.add(message);

                tasks.notify();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
