package com.producer_consumer_pattern;

import com.producer_consumer_pattern.entity.ConsumerRaw;
import com.producer_consumer_pattern.entity.ProducerRaw;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerPatternRaw {
    public static void main(String[] args) {
        Queue<String> tasksQueue = new LinkedList<>();
        ProducerRaw producerRaw = new ProducerRaw(10, tasksQueue);

        ConsumerRaw consumer1 = new ConsumerRaw(tasksQueue);
        ConsumerRaw consumer2 = new ConsumerRaw(tasksQueue);

        new Thread(producerRaw).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
