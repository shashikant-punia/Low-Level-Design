package com.meeting_scheduler.service.impl;

import com.meeting_scheduler.entities.Interval;
import com.meeting_scheduler.entities.Room;
import com.meeting_scheduler.entities.User;
import com.meeting_scheduler.service.IMeetingScheduler;
import com.meeting_scheduler.service.IRoomService;

import java.util.List;


public class MeetingScheduler implements IMeetingScheduler {
    private final IRoomService roomService;

    public MeetingScheduler(IRoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void bookRoom(Interval interval, int requiredCap, List<User> userList) {
        Room room = roomService.checkAvailability(interval, requiredCap);
        roomService.bookRoom(room, interval, userList);
    }
}
