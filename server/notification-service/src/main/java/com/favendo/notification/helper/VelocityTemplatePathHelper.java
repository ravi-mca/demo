package com.favendo.notification.helper;

import com.favendo.notification.utils.Locales;
import org.springframework.stereotype.Component;

@Component
public class VelocityTemplatePathHelper {

    public String getCustomerRegistrationEmailTemplatePath(String templatePath) {
        return String.format(templatePath, Locales.EN_US.getCode());
    }
}
