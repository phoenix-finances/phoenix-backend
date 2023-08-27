package io.omni.financia.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

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

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder -> {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
            DateTimeFormatter zonedDateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            DateTimeFormatter customFullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
            builder.modules(
                    new JavaTimeModule()
                            .addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter))
                            .addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter))
                            .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter))
                            .addSerializer(ZonedDateTime.class, new JsonSerializer<>() {
                                @Override
                                public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                                    gen.writeString(customFullFormatter.format(value));
                                }
                            })
                            .addSerializer(Duration.class, DurationSerializer.INSTANCE)

                            .addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter))
                            .addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter))
                            .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter))
                            .addDeserializer(ZonedDateTime.class, new JsonDeserializer<>() {
                                @Override
                                public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                                    return ZonedDateTime.parse(jsonParser.getValueAsString(), customFullFormatter);
                                }
                            })
                            .addDeserializer(Duration.class, DurationDeserializer.INSTANCE)
            );
            builder.deserializerByType(BigDecimal.class, new NumberDeserializers.BigDecimalDeserializer());
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        //your custom ObjectMapper here
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        jackson2ObjectMapperBuilderCustomizer().customize(builder);
        return builder.build();
    }

    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        logger.info("Configuring Commons Request Logging Filter....");
        CommonsRequestLoggingFilter commonsRequestLoggingFilter = new CommonsRequestLoggingFilter();
        commonsRequestLoggingFilter.setIncludeQueryString(true);
        commonsRequestLoggingFilter.setIncludeClientInfo(true);
        commonsRequestLoggingFilter.setIncludePayload(true);
        commonsRequestLoggingFilter.setIncludeHeaders(true);
        commonsRequestLoggingFilter.setMaxPayloadLength(10000);
        return commonsRequestLoggingFilter;
    }

    @Bean
    public FilterRegistrationBean<CommonsRequestLoggingFilter> registerRequestLogFilter(CommonsRequestLoggingFilter filter) {
        FilterRegistrationBean<CommonsRequestLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(filter);
        return filterRegistrationBean;
    }

    @Bean
    public CustomizableTraceInterceptor customizableTraceInterceptor() {
        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
        customizableTraceInterceptor.setEnterMessage("Entering $[methodName]($[arguments]).");
        customizableTraceInterceptor.setExitMessage("Leaving $[methodName](..), took $[invocationTime]ms.");
        return customizableTraceInterceptor;
    }

    @Bean
    public Advisor traceAdvisor() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(public * io.silverbird.api..*.*(..))");
        return new DefaultPointcutAdvisor(aspectJExpressionPointcut, customizableTraceInterceptor());
    }
}
