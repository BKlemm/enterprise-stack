package com.avondock.app.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "avondock")
@Component
@Data
public class AvondockProperties {

    private Endpoint endpoint;

    static class Endpoint {
        private String protocol;
        private String main;
    }
}
