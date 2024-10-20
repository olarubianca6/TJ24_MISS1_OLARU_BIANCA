package com.example;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import java.util.Properties;

public class CaptchaServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "200");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        Config config = new Config(properties);
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(config);
        ServletContext context = getServletContext();
        context.setAttribute("captchaProducer", captchaProducer);
    }
}