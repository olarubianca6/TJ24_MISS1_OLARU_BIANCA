package com.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter("/*")
public class DecoratorFilter implements Filter {
    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        ServletContext context = request.getServletContext();

        String prelude = (String) context.getAttribute("prelude");
        String coda = (String) context.getAttribute("coda");

        out.println(prelude);
        chain.doFilter(request, response);
        out.println(coda);
    }

    public void destroy() {}

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
