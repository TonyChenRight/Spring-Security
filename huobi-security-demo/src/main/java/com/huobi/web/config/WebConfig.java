package com.huobi.web.config;

import com.huobi.web.filter.TimeFilter;
import com.huobi.web.interceptor.TimeInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 如果使用了  callable DeferredResult方式实现异步，拦截就需要注册异步的拦截器，设置超时、异步执行线程池等
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer.registerCallableInterceptors();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
    }

    /**
     * 注册 filter过滤器
     * @return
     */
//    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();

        TimeFilter timeFilter =new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls=new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }
}
