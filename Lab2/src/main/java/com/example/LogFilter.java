package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogFilter implements Filter {

    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        System.out.println("Request to input.jsp: " + httpReq.getRequestURI());

        chain.doFilter(request, response);
    }

    public void destroy() {}
}
