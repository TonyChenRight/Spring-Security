package com.huobi.security.app.social;

import com.huobi.security.properties.SecurityConstants;
import com.huobi.security.social.support.HuobiSpringSocialConfigurer;

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
        if(StringUtils.equals(beanName, "huobiSocialSecurityConfig")){
            HuobiSpringSocialConfigurer config = (HuobiSpringSocialConfigurer)bean;
            config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
            return config;
        }
        return bean;
    }

}