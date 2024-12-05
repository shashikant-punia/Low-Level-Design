package com.producer_consumer_pattern.entity;

import java.util.Queue;

public class ConsumerRaw implements Runnable {
    private final Queue<String> tasks;

    public ConsumerRaw(Queue<String> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        synchronized (tasks) {
            while (true) {
                while (tasks.isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Consumer is waiting...");
                        tasks.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                String message = tasks.poll();
                System.out.println(message + " is consumed by " + Thread.currentThread().getName());
                tasks.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
