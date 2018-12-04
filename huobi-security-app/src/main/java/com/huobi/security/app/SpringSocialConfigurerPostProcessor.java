package com.huobi.security.app;

import com.huobi.security.social.HuobiSpringSocialConfigurer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName,"huobiSpringSocialConfigurer")){
            HuobiSpringSocialConfigurer configurer=(HuobiSpringSocialConfigurer)bean;
            configurer.signupUrl("/social/sign");
            return configurer;
        }
        return bean;
    }
}
