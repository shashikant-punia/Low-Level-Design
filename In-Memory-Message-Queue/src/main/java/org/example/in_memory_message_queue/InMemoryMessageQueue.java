package org.example.in_memory_message_queue;

import org.example.in_memory_message_queue.entity.Consumer;
import org.example.in_memory_message_queue.entity.Message;
import org.example.in_memory_message_queue.entity.Topic;
import org.example.in_memory_message_queue.service.IQueueService;
import org.example.in_memory_message_queue.service.impl.QueueService;

public class InMemoryMessageQueue {
    public static void main(String[] args) {
        IQueueService queueService = new QueueService();

        Topic topic1 = queueService.createTopic("Topic1");
        Topic topic2 = queueService.createTopic("Topic2");

        Thread producerThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Produced Message: " + i + " by Producer1");
                topic1.addMessage(new Message("Message " + i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread producerThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Produced Message: " + i + " by Producer2");
                topic2.addMessage(new Message("Message " + i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Consumer consumer1 = queueService.createConsumer("Consumer:1");
        Consumer consumer2 = queueService.createConsumer("Consumer:2");

        queueService.subscribeToTopic("Topic1", consumer1);
        queueService.subscribeToTopic("Topic1", consumer2);
        queueService.subscribeToTopic("Topic2", consumer1);

        producerThread1.start();
        producerThread2.start();

    }
}