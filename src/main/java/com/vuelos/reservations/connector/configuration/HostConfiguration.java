package com.vuelos.reservations.connector.configuration;

import lombok.Data;

import java.util.HashMap;

@Data
public class HostConfiguration {

    private String host;

    private int port;

    private HashMap<String, EndpointConfiguration> endpoints;
}
