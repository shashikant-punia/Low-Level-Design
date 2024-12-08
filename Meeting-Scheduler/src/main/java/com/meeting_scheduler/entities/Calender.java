package com.meeting_scheduler.entities;

import lombok.Getter;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Getter
public class Calender {
    private final Set<Interval> bookedSlots;

    public Calender() {
        bookedSlots = new TreeSet<Interval>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.getEnd().isBefore(o2.getStart()))
                    return -1;
                else if (o1.getEnd().isEqual(o2.getStart()))
                    return 0;
                return 1;
            }
        });
    }
}
