package com.example.lab3;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@Logged
public class LoggingInterceptor {
    private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext ctx) throws Exception {
        long startTime = System.currentTimeMillis();
        Object result = ctx.proceed();
        long duration = System.currentTimeMillis() - startTime;

        logger.info("Method " + ctx.getMethod().getName() + " executed in " + duration + "ms");

        return result;
    }
}
