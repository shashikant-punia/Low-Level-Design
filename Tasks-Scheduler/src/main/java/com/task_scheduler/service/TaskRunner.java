package com.task_scheduler.service;

import com.task_scheduler.entities.ScheduledTask;
import com.task_scheduler.repository.ITaskStore;

import java.time.Instant;

public class TaskRunner implements Runnable {
    private final ITaskStore<ScheduledTask> taskStore;
    private volatile boolean running = false;

    public TaskRunner(ITaskStore<ScheduledTask> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void run() {
        this.running = true;
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                ScheduledTask task = taskStore.peek();
                long delay = task.getNextExecutionTime() - Instant.now().toEpochMilli();
                if (delay > 0) {
                    synchronized (this) {
                        wait(delay);
                    }
                } else {
                    task = taskStore.poll();
                    task.execute();

                    if (task.isRecurringTask()) {
                        taskStore.add(task.getNextExecutionTask().get());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.running = false;
        synchronized (this) {
            notify();
        }
    }
}
