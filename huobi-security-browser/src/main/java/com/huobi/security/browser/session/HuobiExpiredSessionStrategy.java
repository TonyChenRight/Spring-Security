package com.huobi.security.browser.session;

import com.huobi.security.properties.SecurityProperties;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * 并发登录导致session失效时，默认的处理策略
 */
public class HuobiExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public HuobiExpiredSessionStrategy(SecurityProperties securityPropertie) {
        super(securityPropertie);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }

}