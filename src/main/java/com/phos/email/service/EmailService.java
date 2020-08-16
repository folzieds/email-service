package com.phos.email.service;

import com.phos.email.models.EmailModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.host}")
    private String host;

    public void sendMail(EmailModel model){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setPassword(password);
        mailSender.setUsername(username);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(model.getEmail());
        mailMessage.setTo(username);
        mailMessage.setSubject("Mail from: " + model.getName());
        mailMessage.setText(model.getMessage());

        mailSender.send(mailMessage);
    }

}
