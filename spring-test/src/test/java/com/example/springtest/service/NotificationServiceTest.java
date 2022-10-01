package com.example.springtest.service;

import com.example.springtest.entity.Notification;
import com.example.springtest.repository.NotificationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService underTest;

    @Captor
    private ArgumentCaptor<Notification> captor;

    @MockBean
    private NotificationRepository notificationRepository;

    @MockBean
    private EmailService emailService;

    @Test
    @DisplayName("sendNotification | expected true | if 'to' parameter is correct")
    public void sendNotification_success(){
        String to = "m@mail.ru";
        String text = "mail text";

        Mockito.doNothing()
                .when(emailService)
                .sendEmail(to, text);

        boolean actual = underTest.sendNotification(to, text);
        assertTrue(actual);
    }

    @Test
    @DisplayName("sendNotification | expected false | if 'to' parameter is incorrect")
    public void sendNotification_fail(){
        String to = "";
        String text = "mail text";

        Mockito.doThrow(IllegalArgumentException.class)
                .when(emailService)
                .sendEmail(to, text);

        boolean actual = underTest.sendNotification(to, text);
        assertFalse(actual);
    }

    @Test
    @DisplayName("getNotificationsCount | expected zero")
    public void getNotificationsCount_success(){
        Mockito.when(emailService.getAllEmails())
                .thenReturn(List.of());

        int actual = underTest.getNotificationsCount();
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("getNotificationsCount | expected exception")
    public void getNotificationsCount_fail(){
        Mockito.when(emailService.getAllEmails())
                .thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, ()->underTest.getNotificationsCount());
    }

    @Test
    @DisplayName("getNotificationsCount | expected called 3 times | if addresses list size = 3")
    public void sendNotifications(){
        List<String> addresses = List.of("One", "Two", "Three");
        String text = "mail text";

        underTest.sendNotifications(addresses, text);
        Mockito.verify(emailService, Mockito.times(3))
                .sendEmail(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    @DisplayName("""
             saveNotification |
             save method called and notification is correct |
             if 'to' and 'text are correct""")
    public void saveNotification(){
        String to = "mike@mail.ru";
        String text = "mail text";

        underTest.saveNotification(text, to);
        Mockito.verify(notificationRepository).save(captor.capture());
        Notification notification = captor.getValue();

        assertEquals(notification.getText(), text);
        assertEquals(notification.getTo(), to);
    }
}