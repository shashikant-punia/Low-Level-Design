package com.thread_pool.service;

import com.thread_pool.entity.Task;

public interface IThreadPoolExecutor {
    void execute(Task task);
}
