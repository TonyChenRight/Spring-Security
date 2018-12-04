package com.huobi.security.browser;

import com.huobi.security.authentication.AbstractChannelSecurityConfig;
import com.huobi.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.huobi.security.browser.logout.HuobiLogoutSuccessHandler;
import com.huobi.security.properties.SecurityConstants;
import com.huobi.security.properties.SecurityProperties;
import com.huobi.security.validate.code.ValidateCodeSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer huobiSpringSocialConfigurer;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(huobiSpringSocialConfigurer)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                //阻止多次登录行为
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
            .logout()
                .logoutUrl("/signOut")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JESSIONID")
                .and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist")
                        .permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();


//        //        http.httpBasic()       //基本登录（默认方式）
//        //将自定义过滤器添加到用户名密码过滤器之前
//        http.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//            .formLogin()    //表单登录
//                //自定义页面，需要放开授权
//                .loginPage("/authentication/require")
//                //指定自定义登录页面请求路径
//                .loginProcessingUrl("/authentication/form")
//                //指定登录成功自定义处理器
//                .successHandler(huobiAuthenticationSuccessHandler)
//                //指定登录失败自定义处理器
//                .failureHandler(huobiAuthenticationFailureHandler)
//            .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//                .userDetailsService(userDetailsService)
//            .and()
//                .authorizeRequests()    //所有请求都需要认证
//                //登录页显示不需要认证
//                .antMatchers("/authentication/require",
//                        securityProperties.getBrowser().getLoginPage(),
//                        "/code/*").permitAll()
//                .anyRequest().authenticated()
//            .and()
//                //禁用 csrf 验证
//                .csrf().disable()
//                .apply(smsCodeAuthenticationSecurityConfig);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 首次使用，创建后注释
//       tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
