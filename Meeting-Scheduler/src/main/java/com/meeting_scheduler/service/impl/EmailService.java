package com.meeting_scheduler.service.impl;

import com.meeting_scheduler.service.IEmailService;

public class EmailService implements IEmailService {

    @Override
    public void sendEmail(String to) {
        System.out.println("Sending email to " + to);
    }
}
