package com.meeting_scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Room {
    private final String roomId;
    private int capacity;
    private final Calender calender;
}
