package com.example.Tax.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
    									HttpServletResponse response,
    									AuthenticationException exception) throws IOException,ServletException {

    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errorMessage = "Login failed. Check your ID or password.";
        String jsonResponse = "{\"errorMessage\":\"" + errorMessage + "\"}";

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}

//@Component
//public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                        AuthenticationException exception) throws IOException, ServletException {
//        String errorMessage = "로그인에 실패했습니다. 유효하지 않은 사용자 정보 또는 비밀번호입니다.";
//        request.getSession().setAttribute("errorMessage", errorMessage);
//    }
//}
