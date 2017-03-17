package com.favendo.portal.contextconfig;

import com.favendo.commons.exception.StorecastExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;

public class ApiGateWayApplication extends ResourceConfig {

    public ApiGateWayApplication() {
        registerExceptionMappers();
    }

    private void registerExceptionMappers() {
        register(StorecastExceptionMapper.class);
    }
}
