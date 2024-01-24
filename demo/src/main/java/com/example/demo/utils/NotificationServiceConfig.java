package com.example.demo.utils;

import com.utils.notification.services.EmailNotificationService;
import com.utils.notification.services.NotificationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationServiceConfig{
    @Bean(name ="textNotificationService")
    public NotificationService textService() {
        return new TextNotificationService();
    }

    @Bean(name = "emailNotificationService")
   // @ConditionalOnMissingBean(NotificationService.class)
    public NotificationService emailService() {
        return new EmailNotificationService(){
            @Override
            public void sendEmails() {
                System.out.println("Sending emails");
                System.out.println("Emails sent to Employees on Outlook!");
            }
        };
    }

}
