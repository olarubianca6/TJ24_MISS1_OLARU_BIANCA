package com.example;

import javax.servlet.*;

public class WebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String prelude = context.getInitParameter("prelude");
        String coda = context.getInitParameter("coda");

        context.setAttribute( "prelude", prelude);
        context.setAttribute("coda", coda);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
