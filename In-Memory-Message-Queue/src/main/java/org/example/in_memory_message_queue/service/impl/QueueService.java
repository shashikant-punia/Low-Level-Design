package org.example.in_memory_message_queue.service.impl;

import org.example.in_memory_message_queue.entity.Consumer;
import org.example.in_memory_message_queue.entity.Topic;
import org.example.in_memory_message_queue.service.IQueueService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QueueService implements IQueueService {
    private final Map<String, Topic> topics;

    public QueueService() {
        this.topics = new ConcurrentHashMap<>();
    }

    @Override
    public Topic createTopic(String topicName) {
        Topic topic = new Topic(topicName);
        this.topics.put(topicName, topic);
        return topic;
    }

    @Override
    public Consumer createConsumer(String consumerId) {
        return new Consumer(consumerId);
    }

    @Override
    public void subscribeToTopic(String topicName, Consumer consumer) {
        Topic topic = this.topics.get(topicName);
        topic.subscribe(consumer);
    }
}
