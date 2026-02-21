package org.example.teahouse.core.error;

import io.micrometer.tracing.Tracer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(Tracer.class)
@AutoConfigureAfter(name = "org.springframework.boot.micrometer.tracing.autoconfigure.MicrometerTracingAutoConfiguration")
public class ExceptionHandlerAutoConfiguration {
    @Bean
    @ConditionalOnBean(Tracer.class)
    CommonExceptionHandler commonExceptionHandler(Tracer tracer) {
        return new CommonExceptionHandler(tracer);
    }
}
