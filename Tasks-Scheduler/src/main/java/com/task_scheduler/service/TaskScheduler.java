package com.task_scheduler.service;

import com.task_scheduler.entities.ScheduledTask;
import com.task_scheduler.repository.ITaskStore;

import java.util.ArrayList;
import java.util.List;

public class TaskScheduler {
    private int numOfThreads;

    private final ITaskStore<ScheduledTask> taskStore;

    private final List<TaskRunner> taskRunners = new ArrayList<>();

    private final List<Thread> threads = new ArrayList<>();

    public TaskScheduler(int numOfThreads, ITaskStore<ScheduledTask> taskStore) {
        this.numOfThreads = numOfThreads;
        this.taskStore = taskStore;
        for (int i = 0; i < numOfThreads; i++) {
            TaskRunner taskRunner = new TaskRunner(taskStore);
            Thread thread = new Thread(taskRunner);
            taskRunners.add(taskRunner);
            threads.add(thread);
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        taskRunners.forEach(TaskRunner::stop);
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
