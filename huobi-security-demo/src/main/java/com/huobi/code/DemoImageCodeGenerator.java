package com.huobi.code;

import com.huobi.security.validate.code.image.ImageCode;
import com.huobi.security.validate.code.ValidateCodeGenerator;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义验证码生成，覆盖 core包中的实现
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
