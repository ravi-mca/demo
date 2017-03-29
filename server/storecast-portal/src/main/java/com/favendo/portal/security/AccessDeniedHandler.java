package com.favendo.portal.security;


import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.builder.AccessDeniedBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;


@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Autowired
    private AccessDeniedBuilder accessDeniedBuilder;


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.setStatus(FORBIDDEN.getStatusCode());
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON(accessDeniedBuilder.buildAccessDenied()));
        httpServletResponse.getWriter().close();
    }
}
