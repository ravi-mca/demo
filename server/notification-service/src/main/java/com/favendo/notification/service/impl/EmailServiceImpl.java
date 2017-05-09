package com.favendo.notification.service.impl;

import com.favendo.notification.service.EmailService;
import com.favendo.notification.service.VelocityTemplateService;
import com.favendo.notification.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private VelocityTemplateService velocityTemplateService;

    public void sendEmail(String recipient, String subject, String templatePath, Map<String, Object> context) {
        emailUtil.sendEmail(recipient, subject, velocityTemplateService.mergeTemplate(templatePath, context));
    }
}
