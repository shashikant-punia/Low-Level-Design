package com.task_scheduler.repository;

import com.task_scheduler.entities.ScheduledTask;

public interface ITaskStore<T extends ScheduledTask> {
    T poll();

    T peek();

    void add(T task);
}
