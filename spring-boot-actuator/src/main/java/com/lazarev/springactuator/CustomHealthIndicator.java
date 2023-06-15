package com.lazarev.springactuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component("my-health-indicator")
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        int errorCode = 0;
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }
}
