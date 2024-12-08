package com.thread_pool.entity;

import com.thread_pool.service.impl.ThreadPoolExecutor;

import java.util.Queue;

public class PoolThread implements Runnable {
    private final ThreadPoolExecutor executor;
    private final String threadName;
    private final Queue<Task> tasks;

    private volatile boolean running;

    public PoolThread(ThreadPoolExecutor executor, Queue<Task> tasks, String threadName) {
        this.executor = executor;
        this.tasks = tasks;
        this.threadName = threadName;
        this.running = true;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        tasks.wait();
                    }
                    Task task = tasks.poll();
                    System.out.println(threadName + " executing: " + task.getId());
                }
//                synchronized (executor) {
//                    executor.notify();
//                }
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}
