package org.example.in_memory_message_queue.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerWorker implements Runnable {
    private int offset;

    private final Consumer consumer;
    private final Topic topic;

    private final AtomicBoolean isRunning;

    public ConsumerWorker(Consumer consumer, Topic topic) {
        this.consumer = consumer;
        this.topic = topic;
        this.offset = 0;
        this.isRunning = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        synchronized (topic) {
            while (isRunning.get()) {
                while (offset >= topic.getQueueSize()) {
                    try {
//                        System.out.println("Waiting for elements in topic: " + topic.getTopicName());
                        topic.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                Message message = topic.getMessage(offset);
                offset += 1;
                System.out.println(consumer.consumerId() + " received message: " + message.messageContent());
            }
        }
    }

    public void stop() {
        isRunning.compareAndSet(true, false);
    }
}
