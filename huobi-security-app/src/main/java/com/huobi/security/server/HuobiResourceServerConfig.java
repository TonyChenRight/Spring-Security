package com.huobi.security.server;

import com.huobi.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.huobi.security.authentication.FormAuthenticationConfig;
import com.huobi.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.huobi.security.authorize.AuthorizeConfigManager;
import com.huobi.security.validate.code.ValidateCodeSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
public class HuobiResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler huobiAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler huobiAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer huobiSocialSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(huobiSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }

}