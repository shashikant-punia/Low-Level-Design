package com.task_scheduler.entities.impl;

import com.task_scheduler.entities.IExecutionContext;
import com.task_scheduler.entities.ScheduledTask;

import java.util.Optional;

public class RecurringScheduledTask extends ScheduledTask {
    private final long executionTime;

    private final long interval;

    public RecurringScheduledTask(long executionTime, long interval, IExecutionContext executionContext) {
        super(executionContext);
        this.executionTime = executionTime;
        this.interval = interval;
    }

    @Override
    public boolean isRecurringTask() {
        return true;
    }

    @Override
    public long getNextExecutionTime() {
        return executionTime;
    }

    @Override
    public Optional<ScheduledTask> getNextExecutionTask() {
        return Optional.of(new RecurringScheduledTask(executionTime + interval, interval, this.executionContext));
    }
}
