package com.vuelos.reservations.connector.configuration;

import lombok.Data;

@Data
public class EndpointConfiguration {

    private String protocol;

    private String url;

    private int connectionTimeout;

    private int readTimeout;

    private int writeTimeout;
}
