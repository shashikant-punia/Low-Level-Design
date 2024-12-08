package com.task_scheduler.repository.impl;

import com.task_scheduler.entities.ScheduledTask;
import com.task_scheduler.repository.ITaskStore;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTaskStore implements ITaskStore<ScheduledTask> {
    private final PriorityBlockingQueue<ScheduledTask> tasksQueue;

    public PriorityBlockingQueueTaskStore(Comparator<ScheduledTask> comparator, int capacity) {
        this.tasksQueue = new PriorityBlockingQueue<>(capacity, comparator);
    }

    @Override
    public ScheduledTask poll() {
        return tasksQueue.poll();
    }

    @Override
    public ScheduledTask peek() {
        return tasksQueue.peek();
    }

    @Override
    public void add(ScheduledTask task) {
        this.tasksQueue.offer(task);
    }
}
