package com.huobi.security.social.weixin.api;

public interface Weixin {
    WeixinUserInfo getUserInfo(String openId);
}
