package com.task_scheduler.entities.impl;

import com.task_scheduler.entities.IExecutionContext;

public class OneTimeTaskExecutionContext implements IExecutionContext {
    private final String id;

    public OneTimeTaskExecutionContext(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        System.out.println("Executing OneTimeTaskExecutionContext: " + id);
    }
}
