package com.meeting_scheduler.entities;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
public class Interval {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final LocalDateTime start;
    private final LocalDateTime end;

    public Interval(String start, String end) {
        this.start = LocalDateTime.parse(start, dateTimeFormatter);
        this.end = LocalDateTime.parse(end, dateTimeFormatter);
    }
}
