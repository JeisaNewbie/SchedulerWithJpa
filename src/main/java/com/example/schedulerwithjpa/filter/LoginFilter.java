package com.example.schedulerwithjpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();

        log.info("로그인 필터 로직 실행");
        log.info("Request URI: {}", requestURI);

        if (!PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendRedirect( "/login");
                return;
            }
        }

        log.info("Request URI: {}", requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
