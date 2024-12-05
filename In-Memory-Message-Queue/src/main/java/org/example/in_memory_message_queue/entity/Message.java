package org.example.in_memory_message_queue.entity;

import lombok.Getter;

@Getter
public record Message(String messageContent) {
}
