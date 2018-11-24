package com.huobi.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.huobi.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        long start =System.currentTimeMillis();

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        Object obj = pjp.proceed();
        System.out.println("time aspect 耗时："+(System.currentTimeMillis() - start));
        System.out.println("time aspect end");

        return obj;
    }
}
