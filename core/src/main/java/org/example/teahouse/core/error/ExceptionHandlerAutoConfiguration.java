package org.example.teahouse.core.error;

import io.micrometer.tracing.Tracer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import tools.jackson.databind.json.JsonMapper;

@AutoConfiguration
public class ExceptionHandlerAutoConfiguration {
    @Bean
    @ConditionalOnClass(Tracer.class)
    @ConditionalOnBean(Tracer.class)
    CommonExceptionHandler commonExceptionHandler(Tracer tracer) {
        return new CommonExceptionHandler(tracer);
    }

    // Workaround for https://github.com/spring-projects/spring-boot/issues/49265
    @Bean
    JacksonJsonHttpMessageConverter jacksonJsonHttpMessageConverter(JsonMapper.Builder builder) {
        return new JacksonJsonHttpMessageConverter(builder);
    }
}
