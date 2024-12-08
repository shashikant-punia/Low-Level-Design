package com.blocking_queue;

import com.blocking_queue.entity.BlockingQueue;
import com.blocking_queue.entity.impl.ArrayBlockingQueue;

public class BlockingQueueMain {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(10);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                blockingQueue.offer(i + "");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                blockingQueue.take();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(3000);

        t1.interrupt();
        t1.join();

        t2.interrupt();
        t2.join();
    }
}