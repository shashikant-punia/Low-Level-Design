package com.meeting_scheduler.service;

import com.meeting_scheduler.entities.Interval;
import com.meeting_scheduler.entities.User;

import java.util.List;

public interface IMeetingScheduler {
    void bookRoom(Interval interval, int requiredCap, List<User> userList);
}
