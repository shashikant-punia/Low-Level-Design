package com.meeting_scheduler.service;

import com.meeting_scheduler.entities.Interval;
import com.meeting_scheduler.entities.Room;
import com.meeting_scheduler.entities.User;

import java.util.List;

public interface IRoomService {
    Room checkAvailability(Interval interval, int requiredCap);

    void bookRoom(Room room, Interval interval, List<User> userList);
}
