# Problem statement:

Design an In-Memory Distributed Queue like Kafka.

## Requirements Gathering:

1. The queue should be in-memory and does not require access to the file system.
2. There can be multiple topics in the queue.
3. A (string) message can be published on a topic by a producer/publisher and consumers/subscribers topic to receive the messages.
4. There can be multiple producers and consumers.
5. A producer can publish to multiple topics.
6. A consumer can listen from multiple topics.
7. The consumer should print "<consumer_id> received <message>" on receiving the message.
8. The queue system should be multithreaded, i.e., messages can be produced or consumed in parallel producers/consumers.