package org.example.teahouse.tea.config;

import io.micrometer.observation.ObservationPredicate;
import org.example.teahouse.core.actuator.health.HealthClientAdapter;
import org.example.teahouse.tea.tealeaf.TealeafClient;
import org.example.teahouse.tea.water.WaterClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.observation.ServerRequestObservationContext;

@AutoConfiguration
public class ActuatorConfig {
    @Bean
    public HealthIndicator tealeafServiceHealthIndicator(TealeafClient tealeafClient) {
        return new HealthClientAdapter(tealeafClient);
    }

    @Bean
    public HealthIndicator waterServiceHealthIndicator(WaterClient waterClient) {
        return new HealthClientAdapter(waterClient);
    }

    @Bean
    ObservationPredicate noUiResourceObservations() {
        return (name, context) -> {
            if (name.equals("http.server.requests") && context instanceof ServerRequestObservationContext serverContext && serverContext.getCarrier() != null) {
                return !serverContext.getCarrier().getRequestURI().startsWith("/assets/");
            }
            else {
                return true;
            }
        };
    }
}
