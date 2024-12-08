package com.blocking_queue.entity.impl;

import com.blocking_queue.entity.BlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

public class ArrayBlockingQueue implements BlockingQueue<String> {
    private final int capacity;

    private final Queue<String> queue;

    public ArrayBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    @Override
    public synchronized String take() {
        String message = "";
        try {
            while (queue.isEmpty()) {
                System.out.println("queue is empty, Waiting...");
                this.wait();
            }
            message = queue.poll();
            System.out.println("Thread: " + Thread.currentThread().getName() + " consumed " + message);
            this.notifyAll();
            return message;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

    @Override
    public synchronized void offer(String message) {
        try {
            while (queue.size() >= capacity) {
                System.out.println("queue is full, Waiting...");
                this.wait();
            }
            queue.offer(message);
            System.out.println("Thread: " + Thread.currentThread().getName() + " produced " + message);
            this.notify();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
