package com.archiadmin.common.config.security.handler;

import com.archiadmin.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonStr = String.format("""
            {
              "status": %d,
              "code": "%s",
              "message": "%s"
            }
            """, errorCode.getStatus().value(), errorCode.getCode(), errorCode.getMessage());

        response.getWriter().write(jsonStr);
    }
}
