package com.meeting_scheduler.service.impl;

import com.meeting_scheduler.entities.Interval;
import com.meeting_scheduler.entities.Room;
import com.meeting_scheduler.entities.User;
import com.meeting_scheduler.service.ICalenderService;
import com.meeting_scheduler.service.IEmailService;
import com.meeting_scheduler.service.IRoomService;

import java.util.List;

public class RoomService implements IRoomService {
    private final ICalenderService calenderService;
    private final List<Room> rooms;
    private final IEmailService emailService;

    public RoomService(ICalenderService calenderService, List<Room> rooms, IEmailService emailService) {
        this.calenderService = calenderService;
        this.rooms = rooms;
        this.emailService = emailService;
    }

    @Override
    public Room checkAvailability(Interval interval, int requiredCap) {
        for (Room room : rooms) {
            if (room.getCapacity() >= requiredCap && calenderService.checkAvailability(room, interval))
                return room;
        }
        return null;
    }

    @Override
    public synchronized void bookRoom(Room room, Interval interval, List<User> userList) {
        calenderService.block(room, interval);
        for (User user : userList) {
            emailService.sendEmail(user.getEmailId());
        }
    }


}
