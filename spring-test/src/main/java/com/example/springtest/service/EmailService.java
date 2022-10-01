package com.example.springtest.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    public List<String> getAllEmails(){
        return List.of("Mail1", "Mail2", "Mail3");
    }

    public void sendEmail(String to, String text){
        if(to == null || to.isBlank()){
            throw new IllegalArgumentException("Parameter 'to' cannot be null or empty");
        }
        System.out.printf("Email sent to '%s' with test '%s'%n", to, text);
    }

    public String generateMail(String to, String text){
        if(to == null || to.isBlank()){
            throw new IllegalArgumentException("Parameter 'to' cannot be null or empty");
        }
        return "Email to '%s' with text '%s'".formatted(to, text);
    }


}
