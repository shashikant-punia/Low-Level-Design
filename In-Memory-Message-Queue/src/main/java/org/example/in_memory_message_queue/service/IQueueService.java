package org.example.in_memory_message_queue.service;

import org.example.in_memory_message_queue.entity.Consumer;
import org.example.in_memory_message_queue.entity.Topic;

public interface IQueueService {
    Topic createTopic(String topicName);

    Consumer createConsumer(String consumerId);

    void subscribeToTopic(String topicName, Consumer consumer);
}
