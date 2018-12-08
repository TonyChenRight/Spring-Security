package com.huobi.security.validate.code.sms;

public interface SmsCodeSender {

    /**
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);

}