package com.example.chatbot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSchedulingService {

    @Scheduled(cron = "0 0 13 * * ?") // Schedule daily at 5:48 PM
    public void sendDailyEmail() {
        DailyEmailScheduler.sendDailyEmail();
    }
    
    
}



