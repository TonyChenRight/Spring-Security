package com.huobi.security.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;

/**
 * 验证码存储接口
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code
     * @param validateCodeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    /**
     * 删除验证码
     * @param request
     * @param validateCodeType
     */
    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     * @param request
     * @param codeType
     * @return
     */
    ValidateCode get(ServletWebRequest request, ValidateCodeType codeType);
}
