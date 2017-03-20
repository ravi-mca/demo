package com.favendo.portal.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException {
		response.setStatus(SC_UNAUTHORIZED);
		response.setContentType(APPLICATION_JSON);
		PrintWriter writer = response.getWriter();
		mapper.writeValue(writer, "Authentication entry point failed");
		writer.flush();
	}
}
