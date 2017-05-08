package com.favendo.notification.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Map;

@Service
public class VelocityTemplateService {
    private static final String VELOCITY_TEMPLATE_ENCODING = "UTF-8";

    @Autowired
    private VelocityEngine velocityEngine;

    public String mergeTemplate(String templateLocation, Map<String, Object> model) {
        return VelocityEngineUtils
                .mergeTemplateIntoString(velocityEngine, templateLocation, VELOCITY_TEMPLATE_ENCODING, model);
    }
}