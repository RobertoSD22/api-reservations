package com.vuelos.reservations.connector.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Data
@Configuration
@ConfigurationProperties(prefix = "http-connector")
public class HttpConnectorConfiguration {

    private HashMap<String, HostConfiguration> hosts;
}
