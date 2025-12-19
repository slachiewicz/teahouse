package org.example.teahouse.core.actuator.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;

import java.util.Optional;

@RequiredArgsConstructor
public class HealthClientAdapter implements HealthIndicator {
    private final HealthClient healthClient;

    @Override
    public Health health() {
        try {
            return Optional.ofNullable(healthClient.health())
                .map(HealthResponse::toHealth)
                .orElse(Health.down()
                    .withDetail("error", "null response")
                    .build()
                );
        }
        catch (Exception exception) {
            return Health.down(exception).build();
        }
    }
}
