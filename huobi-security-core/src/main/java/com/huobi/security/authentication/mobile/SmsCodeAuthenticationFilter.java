package com.huobi.security.authentication.mobile;

import com.huobi.security.properties.SecurityConstants;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
        private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_MOBILE;
        private boolean postOnly = true;

        public SmsCodeAuthenticationFilter() {
            super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            if (this.postOnly && !request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
            } else {
                String mobile = this.obtainMobile(request);
                if (mobile == null) {
                    mobile = "";
                }
                mobile = mobile.trim();

                SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
                this.setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }

    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return this.mobileParameter;
    }
}
