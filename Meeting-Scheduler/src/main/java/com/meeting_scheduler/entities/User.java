package com.meeting_scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private final String username;
    private final String emailId;
}
