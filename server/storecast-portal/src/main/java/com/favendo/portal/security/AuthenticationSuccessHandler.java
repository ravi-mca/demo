package com.favendo.portal.security;

import com.favendo.commons.utils.JsonMapper;
import com.favendo.user.service.builder.AuthenticationBuilder;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.dto.AuthenticationSuccessDto;
import com.favendo.user.service.jwt.TokenGenerator;
import com.favendo.user.service.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private AuthenticationBuilder authenticationBuilder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequestt, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JsonMapper.objectToJSON(getAuthenticationSuccessDto()));
    }

    private AuthenticationSuccessDto getAuthenticationSuccessDto() {
        User storecastUser = UserContextHolder.getLoggedInUser();
        return authenticationBuilder.buildAuthenticationSuccess(storecastUser.getRoles(),
                tokenGenerator.generateToken(storecastUser));
    }
}
