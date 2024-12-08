package com.meeting_scheduler.service.impl;

import com.meeting_scheduler.entities.Calender;
import com.meeting_scheduler.entities.Interval;
import com.meeting_scheduler.entities.Room;
import com.meeting_scheduler.service.ICalenderService;

import java.util.Map;

public class CalenderService implements ICalenderService {
    private final Map<Room, Calender> calenderMap;

    public CalenderService(Map<Room, Calender> calenderMap) {
        this.calenderMap = calenderMap;
    }

    @Override
    public boolean checkAvailability(Room room, Interval interval) {
        Calender calender = calenderMap.get(room);
        for (Interval bookedSlot : calender.getBookedSlots()) {
            if (!(interval.getEnd().isBefore(bookedSlot.getStart())) || interval.getStart().isAfter(bookedSlot.getEnd())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized void block(Room room, Interval interval) {
        calenderMap.get(room).getBookedSlots().add(interval);
    }
}
