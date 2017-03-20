package com.favendo.portal.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", config.getInitParameter("Access-Control-Allow-Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", config.getInitParameter("Access-Control-Allow-Methods"));
        httpResponse.setHeader("Access-Control-Max-Age", config.getInitParameter("Access-Control-Max-Age"));
        if (config.getInitParameter("Access-Control-Allow-Headers").equals("*")) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String reqHead = httpRequest.getHeader("Access-Control-Request-Headers");

            if (!StringUtils.isEmpty(reqHead)) {
                httpResponse.addHeader("Access-Control-Allow-Headers", reqHead);
            }
        } else
            httpResponse.setHeader("Access-Control-Allow-Headers", config.getInitParameter("Access-Control-Allow-Headers"));
        chain.doFilter(request, response);
    }
}
