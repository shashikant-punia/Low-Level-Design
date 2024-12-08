package com.task_scheduler;

import com.task_scheduler.entities.ExecutorConfig;
import com.task_scheduler.entities.ScheduledTask;
import com.task_scheduler.entities.impl.OneTimeScheduledTask;
import com.task_scheduler.entities.impl.OneTimeTaskExecutionContext;
import com.task_scheduler.entities.impl.RecurringScheduledTask;
import com.task_scheduler.entities.impl.RecurringTaskExecutionContext;
import com.task_scheduler.repository.ITaskStore;
import com.task_scheduler.repository.impl.PriorityBlockingQueueTaskStore;
import com.task_scheduler.service.TaskScheduler;

import java.time.Instant;
import java.util.Comparator;

public class TaskSchedulerMain {
    public static void main(String[] args) throws InterruptedException {
        Comparator<ScheduledTask> comparator = (task1, task2) -> {
            long executionTime1 = task1.getNextExecutionTime();
            long executionTime2 = task2.getNextExecutionTime();

            if (executionTime1 > executionTime2) {
                return 1;
            } else if (executionTime1 < executionTime2) {
                return -1;
            }
            return 0;
        };
        ITaskStore<ScheduledTask> taskStore = new PriorityBlockingQueueTaskStore(comparator, 10);

        for (int i = 0; i < 5; i++) {
            taskStore.add(new OneTimeScheduledTask(new OneTimeTaskExecutionContext(i + ""), Instant.now().toEpochMilli() + 50));
        }
        for (int i = 5; i < 10; i++) {
            taskStore.add(new RecurringScheduledTask(Instant.now().toEpochMilli() + 100, 100, new RecurringTaskExecutionContext(i + "")));
        }

        TaskScheduler taskScheduler = new TaskScheduler(new ExecutorConfig(3), taskStore);

        taskScheduler.start();

        Thread.sleep(5000);

        taskScheduler.stop();
    }
}