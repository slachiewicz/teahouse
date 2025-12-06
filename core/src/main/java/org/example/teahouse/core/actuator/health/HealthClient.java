package org.example.teahouse.core.actuator.health;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthContributor;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Only single-level inheritance supported by Feign and {@link HealthIndicator} extends {@link HealthContributor}
 * so returning a Health instance won't work.
 * Also, {@link Health} is not deserializable, see {@link HealthResponse}.
 */
public interface HealthClient {
    @GetMapping("/actuator/health")
    HealthResponse health();
}
