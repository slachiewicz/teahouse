package org.example.teahouse.sba;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.PropertySource;

@EnableAdminServer
@SpringBootApplication
@PropertySource("classpath:build.properties")
public class SbaApplication {
    static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SbaApplication.class);
        springApplication.setApplicationStartup(new BufferingApplicationStartup(10_000));
        springApplication.run(args);
    }
}
