package com.task_scheduler.entities;

import java.util.Optional;

public abstract class ScheduledTask {
    protected final IExecutionContext executionContext;

    public ScheduledTask(IExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    public void execute() {
        executionContext.execute();
    }

    public abstract boolean isRecurringTask();

    public abstract long getNextExecutionTime();

    public abstract Optional<ScheduledTask> getNextExecutionTask();
}
