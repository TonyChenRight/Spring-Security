package com.huobi.security.validate.code;

import com.huobi.security.properties.SecurityProperties;
import com.huobi.security.validate.code.image.ImageCodeGenerator;
import com.huobi.security.validate.code.sms.DefaultSmsCodeSender;
import com.huobi.security.validate.code.sms.SmsCodeSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 条件：不存在名字为 imageCodeGenerator 的bean时才使用这个配置
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 条件： 只要有实现相同接口的类存在，就不再加载
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
