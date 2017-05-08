package com.favendo.notification.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.favendo.notification.constant.NotificationConstant.*;

@Component
public class EmailUtil {

    @Value("${from.email.address}")
    private String fromEmailAddress;

    @Value("${from.email.password}")
    private String fromEmailPassword;

    @Value("${mail.smtp.host.name}")
    private String mailSmtpHostName;

    @Value("${mail.smtp.socketFactory.port}")
    private String mailSmtpSocketFactoryPort;

    @Value("${mail.smtp.socketFactory.class}")
    private String getMailSmtpSocketFactoryClass;

    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${mailSmtpPort}")
    private String mailSmtpPort;

    public void sendEmail(String recipient,String subject,String body){
        Session session = Session.getDefaultInstance(buildProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmailAddress,fromEmailPassword);
                    }
                });
        try {
            send(recipient, subject, body, session);
        } catch (MessagingException messagingException) {
            throw new RuntimeException(messagingException);
        }
    }

    private void send(String recipient, String subject, String body, Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(body,CONTENT_TYPE_TEXT_HTML);
        Transport.send(message);
    }

    private Properties buildProperties() {
        Properties properties = new Properties();
        properties.put(MAIL_SMTP_HOST,mailSmtpHostName);
        properties.put(MAIL_SMTP_SOCKET_FACTORY_PORT, mailSmtpSocketFactoryPort);
        properties.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, getMailSmtpSocketFactoryClass);
        properties.put(MAIL_SMTP_AUTH, mailSmtpAuth);
        properties.put(MAIL_SMTP_PORT, mailSmtpPort);
        return properties;
    }
}
