package com.kickproject.user.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlyWayConfig {

    private final Environment env;

    public FlyWayConfig(final Environment env) {
        this.env = env;
        Flyway.configure().baselineOnMigrate(true).table("flyway_schema_history_user").dataSource(
                env.getRequiredProperty("spring.flyway.url"),
                env.getRequiredProperty("spring.flyway.user"),
                env.getRequiredProperty("spring.flyway.password")).load().migrate();
    }
}