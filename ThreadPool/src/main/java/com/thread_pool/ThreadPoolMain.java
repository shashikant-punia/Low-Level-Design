package com.thread_pool;

import com.thread_pool.entity.Task;
import com.thread_pool.service.impl.ThreadPoolExecutor;

public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 2, 4);

        new Thread(() -> {
            int count = 3;
            int messageNo = 1;
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < 10; j++) {
                    int finalJ = messageNo;
                    messageNo++;
                    threadPoolExecutor.execute(new Task(finalJ));
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        threadPoolExecutor.stop();
    }
}