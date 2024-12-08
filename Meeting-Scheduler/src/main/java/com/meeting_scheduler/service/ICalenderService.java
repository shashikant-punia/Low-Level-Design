package com.meeting_scheduler.service;

import com.meeting_scheduler.entities.Interval;
import com.meeting_scheduler.entities.Room;

public interface ICalenderService {
    boolean checkAvailability(Room room, Interval interval);

    void block(Room room, Interval interval);
}
