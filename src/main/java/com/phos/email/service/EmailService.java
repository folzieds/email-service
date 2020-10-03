package com.phos.email.service;

import com.phos.email.models.EmailModel;
import com.phos.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${mail.recipient}")
    private String recipient;

    public void send(EmailModel emailModel){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",port);
        properties.put("mail.smtp.socketFactory.port",port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        //creating a session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        //creating the message
        try{
            Message mail = new MimeMessage(session);
            mail.setFrom(new InternetAddress(emailModel.getEmail()));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            mail.setSubject(emailModel.getName());
            mail.setText(emailModel.getEmail()+ "\n\n" + emailModel.getMessage());

            //send message
            Transport.send(mail);

            emailRepository.save(emailModel);

        }catch (MessagingException e){
            throw new RuntimeException("Message was not sent");
        }
    }

    public EmailModel getEmail(long id){
        Optional<EmailModel> modelOptional = emailRepository.findById(id);
        return modelOptional.orElse(null);
    }

    public List<EmailModel> search(String name){
        return emailRepository.findAllByEmailContainingOrNameContainingOrMessageContainingOrderByIdDesc(name,name,name);
    }

}
