package org.example.teahouse.core.log.access;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.apache.catalina.Context;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class AccessLogAutoConfiguration {

    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.addContextCustomizers(this::tomcatContextCustomizer);
    }

    private void tomcatContextCustomizer(Context context) {
        LogbackValve logbackValve = new LogbackValve();
        logbackValve.setAsyncSupported(true);
        logbackValve.setFilename("logback-access.xml");
        context.getPipeline().addValve(logbackValve);
    }
}
