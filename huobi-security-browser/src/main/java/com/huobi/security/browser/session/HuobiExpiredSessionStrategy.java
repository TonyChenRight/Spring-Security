package com.huobi.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

import javax.servlet.ServletException;

public class HuobiExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public HuobiExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
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
