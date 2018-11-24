package com.huobi.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        HandlerMethod method=(HandlerMethod)handler;
        System.out.println(method.getBean().getClass().getName());
        System.out.println(method.getMethod().getName());

        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");

        Long start =(Long)request.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(System.currentTimeMillis() - start));
    }

    /**
     * 异常如果之前处理过了，这里异常将会为空
     * 不光拦截自定义Controller，还会拦截spring提供的controller，如错误处理 BasicErrorController
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        Long start =(Long)request.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+(System.currentTimeMillis() - start));
        System.out.println("ex is "+ex);
    }
}
