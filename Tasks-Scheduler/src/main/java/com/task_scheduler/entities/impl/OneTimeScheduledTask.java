package com.task_scheduler.entities.impl;

import com.task_scheduler.entities.IExecutionContext;
import com.task_scheduler.entities.ScheduledTask;

import java.util.Optional;

public class OneTimeScheduledTask extends ScheduledTask {
    private final long executionTime;

    public OneTimeScheduledTask(IExecutionContext executionContext, long executionTime) {
        super(executionContext);
        this.executionTime = executionTime;
    }

    @Override
    public boolean isRecurringTask() {
        return false;
    }

    @Override
    public long getNextExecutionTime() {
        return executionTime;
    }

    @Override
    public Optional<ScheduledTask> getNextExecutionTask() {
        return Optional.empty();
    }
}
