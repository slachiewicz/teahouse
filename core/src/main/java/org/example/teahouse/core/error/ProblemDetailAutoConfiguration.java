package org.example.teahouse.core.error;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import tools.jackson.databind.json.JsonMapper;

@AutoConfiguration
public class ProblemDetailAutoConfiguration {
    // Workaround for https://github.com/spring-projects/spring-boot/issues/49265
    @Bean
    JacksonJsonHttpMessageConverter jacksonJsonHttpMessageConverter(JsonMapper.Builder builder) {
        return new JacksonJsonHttpMessageConverter(builder);
    }
}
