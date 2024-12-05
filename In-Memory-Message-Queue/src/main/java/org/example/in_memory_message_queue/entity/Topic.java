package org.example.in_memory_message_queue.entity;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Topic {
    @Getter
    private final String topicName;

    private final List<Message> messageQueue;

    private final Map<String, Consumer> subscriberMap;

    private final Map<Consumer, Thread> subscriberWokerMap;

    public Topic(String topicName) {
        this.topicName = topicName;
        this.messageQueue = new ArrayList<>();
        this.subscriberMap = new ConcurrentHashMap<>();
        this.subscriberWokerMap = new ConcurrentHashMap<>();
    }

    public int getQueueSize() {
        return messageQueue.size();
    }

    public Message getMessage(int offset) {
        if (offset >= messageQueue.size()) {
            return null;
        }
        return messageQueue.get(offset);
    }

    public synchronized void addMessage(Message message) {
        this.messageQueue.add(message);
        this.notifyAll();
    }

    public void subscribe(Consumer consumer) {
        subscriberMap.put(consumer.consumerId(), consumer);

        Thread worker = new Thread(new ConsumerWorker(consumer, this));
        worker.start();
        subscriberWokerMap.put(consumer, worker);
    }

    public void stopConsumer(Consumer consumer) {
//        subscriberWokerMap.get(consumer).interrupt();
    }
}
