package com.favendo.notification.service.impl;

import com.favendo.notification.service.EmailService;
import com.favendo.notification.service.VelocityTemplateService;
import com.favendo.notification.utils.EmailUtil;
import com.favendo.notification.utils.Locales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.io.File.separator;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private VelocityTemplateService velocityTemplateService;

    @Value("${admin.welcome.email.template}")
    private String templateName;

    @Value("${email.template.path}")
    private String templatePath;

    public void sendEmail(String recipient,String subject,String templatePath,Map<String,Object> context){
        emailUtil.sendEmail(recipient,subject,velocityTemplateService.mergeTemplate(templatePath, context));
    }

    public void sendEmail(String recipient,String subject,String message){
        String path = getAdminEmailTemplateLocation();
        Map<String, Object> emailContext = buildEmailContext();
        String emailBody = velocityTemplateService.mergeTemplate(path, emailContext);
        emailUtil.sendEmail(recipient,subject,emailBody);
    }

    private String getAdminEmailTemplateLocation() {
        return templatePath.concat(separator).concat(Locales.EN_US.getCode()).concat(separator).concat(templateName);
    }

    public Map<String, Object> buildEmailContext() {
        Map<String, Object> emailVelocityContext = new HashMap<>();
        emailVelocityContext.put("firstName","Kunjan");
        emailVelocityContext.put("lastName","Shah");
        emailVelocityContext.put("link","http://localhost:8080/storecast/resetpassword");
        emailVelocityContext.put("emailaddress","kunjan.shah@zymr.com");
        return emailVelocityContext;
    }

}
