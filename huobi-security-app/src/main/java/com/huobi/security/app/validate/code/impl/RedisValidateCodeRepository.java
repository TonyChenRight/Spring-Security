package com.huobi.security.app.validate.code.impl;

import com.huobi.security.validate.code.ValidateCode;
import com.huobi.security.validate.code.ValidateCodeException;
import com.huobi.security.validate.code.ValidateCodeRepository;
import com.huobi.security.validate.code.ValidateCodeType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request,validateCodeType),code,30, TimeUnit.MINUTES);
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request,validateCodeType));
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType codeType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, codeType));
        if(value == null){
            return null;
        }
        return (ValidateCode)value;
    }

    private Object buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId=  request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return "code:"+validateCodeType.toString().toLowerCase()+":"+deviceId;
    }
}
