package com.favendo.notification.contextconfig;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.favendo.notification.constant.NotificationConstant.*;

@Configuration
@ComponentScan({"com.favendo.notification.*"})
public class NotificationContextConfig {

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RESOURCE_LOADER,RESOURCE_LOADER_NAME);
        velocityEngine.setProperty(CLASS_RESOURCE_LOADER, CLASS_RESOURCE_LOADER_NAME);
        return velocityEngine;
    }
}
