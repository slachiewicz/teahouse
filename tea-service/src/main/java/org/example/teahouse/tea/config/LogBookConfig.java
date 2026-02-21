package org.example.teahouse.tea.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.clientconfig.HttpClient5FeignConfiguration;
import org.springframework.context.annotation.Bean;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient5.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient5.LogbookHttpResponseInterceptor;

@AutoConfiguration
public class LogBookConfig {
    @Bean
    public HttpClient5FeignConfiguration.HttpClientBuilderCustomizer httpClientBuilderCustomizer(Logbook logbook) {
        return builder -> builder
            .addRequestInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
            .addResponseInterceptorFirst(new LogbookHttpResponseInterceptor());
    }
}
