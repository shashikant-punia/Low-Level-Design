package com.meeting_scheduler.entities;

import com.meeting_scheduler.service.IEmailService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Room {
    private final String roomId;
    private int capacity;
    private final Calender calender;

    public synchronized void bookRoom(Interval interval, List<User> userList) {
        this.calender.block(interval);
        for (User user : userList) {
            emailService.sendEmail(user.getEmailId());
        }
    }
}
