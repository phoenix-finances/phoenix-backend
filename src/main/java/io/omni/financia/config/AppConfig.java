//package io.omni.financia.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.CommonsRequestLoggingFilter;
//
//@Configuration
//public class AppConfig {
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
//}
