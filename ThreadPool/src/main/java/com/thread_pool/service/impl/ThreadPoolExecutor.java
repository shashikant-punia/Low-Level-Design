package com.thread_pool.service.impl;

import com.thread_pool.entity.PoolThread;
import com.thread_pool.entity.Task;
import com.thread_pool.service.IThreadPoolExecutor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPoolExecutor implements IThreadPoolExecutor {
    private int capacity;

    private int corePoolSize;

    private int maximumPoolSize;

    private final Queue<Task> tasks;

    private final List<PoolThread> poolThreads;

    private final List<Thread> threads;

    public ThreadPoolExecutor(int capacity, int corePoolSize, int maximumPoolSize) {
        this.capacity = capacity;
        this.tasks = new LinkedList<>();
        this.threads = new ArrayList<>();
        this.poolThreads = new ArrayList<>();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;

        for (int i = 0; i < this.corePoolSize; i++) {
            PoolThread poolThread = new PoolThread(this, this.tasks, "Thread: " + i);
            this.poolThreads.add(poolThread);
            Thread thread = new Thread(poolThread);
            thread.start();
            threads.add(thread);
        }
    }

    @Override
    public synchronized void execute(Task task) {
//        try {
//            synchronized (tasks) {
//                while (tasks.size() >= capacity) {
//                    if (threads.size() >= maximumPoolSize) {
//                        System.out.println("Waiting...");
//                        tasks.wait();
//                    } else {
//                        System.out.println("Increasing threadPool size by 1 as needed.");
//                    }
//                }
//
//            }
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }

        tasks.add(task);
        synchronized (tasks) {
            tasks.notify();
        }
    }

    public void stop() throws InterruptedException {
        while (!tasks.isEmpty()) {
            Thread.sleep(50);
        }
        poolThreads.forEach(PoolThread::stop);
        synchronized (tasks) {
            tasks.notifyAll();
        }

        for (Thread thread : threads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
