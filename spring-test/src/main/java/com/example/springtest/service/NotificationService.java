package com.example.springtest.service;

import com.example.springtest.entity.Notification;
import com.example.springtest.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;

    public boolean sendNotification(String to, String text){
        try{
            emailService.sendEmail(to, text);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public void sendNotifications(List<String> addresses, String text){
        for(String to : addresses){
            emailService.sendEmail(to, text);
        }
    }

    public int getNotificationsCount(){
        return emailService.getAllEmails().size();
    }

    public void saveNotification(String text, String to){
        Notification notification = new Notification();
        notification.setText(text);
        notification.setTo(to);
        notification.setSentAt(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}
