package com.task_scheduler.entities.impl;

import com.task_scheduler.entities.IExecutionContext;

public class RecurringTaskExecutionContext implements IExecutionContext {
    private final String id;

    public RecurringTaskExecutionContext(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        System.out.println("Executing Recurring Task Execution Context: " + id);
    }
}
