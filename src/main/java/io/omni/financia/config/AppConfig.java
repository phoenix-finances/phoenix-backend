package io.omni.financia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }


//    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);
//    @Bean
//    public CommonsRequestLoggingFilter requestLoggingFilter() {
//        logger.info("Configuring Commons Request Logging Filter....");
//        CommonsRequestLoggingFilter commonsRequestLoggingFilter = new CommonsRequestLoggingFilter();
//        commonsRequestLoggingFilter.setIncludeQueryString(true);
//        commonsRequestLoggingFilter.setIncludeClientInfo(true);
//        commonsRequestLoggingFilter.setIncludePayload(true);
//        commonsRequestLoggingFilter.setIncludeHeaders(true);
//        commonsRequestLoggingFilter.setMaxPayloadLength(10000);
//        return commonsRequestLoggingFilter;
//    }
//
//    @Bean
//    public FilterRegistrationBean<CommonsRequestLoggingFilter> registerRequestLogFilter(CommonsRequestLoggingFilter filter) {
//        FilterRegistrationBean<CommonsRequestLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.setFilter(filter);
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public CustomizableTraceInterceptor customizableTraceInterceptor() {
//        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
//        customizableTraceInterceptor.setEnterMessage("Entering $[methodName]($[arguments]).");
//        customizableTraceInterceptor.setExitMessage("Leaving $[methodName](..), took $[invocationTime]ms.");
//        return customizableTraceInterceptor;
//    }
//
//    @Bean
//    public Advisor traceAdvisor() {
//        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
//        aspectJExpressionPointcut.setExpression("execution(public * io.silverbird.api..*.*(..))");
//        return new DefaultPointcutAdvisor(aspectJExpressionPointcut, customizableTraceInterceptor());
//    }
}
