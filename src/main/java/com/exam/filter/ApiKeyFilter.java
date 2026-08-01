package com.exam.filter;

import com.exam.constants.Constants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Filtro de seguridad — valida el header X-API-KEY en cada request
 * Si el key no coincide devuelve 401 Unauthorized
 */
@Component
public class ApiKeyFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ApiKeyFilter.class);

    @Value("${api.key:exam-secret-key-2024}")
    private String apiKey;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest)  servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String keyHeader = request.getHeader(Constants.HEADER_API_KEY);

        // Si no trae el header o el key no coincide -> 401
        if (keyHeader == null || !apiKey.equals(keyHeader)) {
            log.warn(Constants.LOG_FILTER_DENIED);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(Constants.CONTENT_TYPE_JSON);
            response.getWriter().write(Constants.MSG_API_KEY_INVALID_JSON);
            return;
        }

        log.info(Constants.LOG_FILTER_OK);
        chain.doFilter(servletRequest, servletResponse);
    }
}

