package com.archiadmin.common.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK); // 200
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonStr = """
            {
              "status": 200,
              "code": "AUTH000",
              "message": "로그인에 성공하였습니다"
            }
            """;
        response.getWriter().write(jsonStr);
    }
}
