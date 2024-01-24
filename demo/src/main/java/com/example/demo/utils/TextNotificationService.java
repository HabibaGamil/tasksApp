package com.example.demo.utils;

import com.utils.notification.services.NotificationService;

public class TextNotificationService implements NotificationService {

    @Override
    public void sendNotification() {
        System.out.println("Text Notification sent to employees!");
    }

}
