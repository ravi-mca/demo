package com.favendo.notification.service;

import java.util.Map;

public interface EmailService {

    void sendEmail(String recipient,String subject,String templatePath,Map<String,Object> context);

    void sendEmail(String recipient, String subject, String message);
}
