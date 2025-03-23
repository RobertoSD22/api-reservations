package com.vuelos.reservations.connector;

import com.vuelos.reservations.connector.configuration.EndpointConfiguration;
import com.vuelos.reservations.connector.configuration.HostConfiguration;
import com.vuelos.reservations.connector.configuration.HttpConnectorConfiguration;
import com.vuelos.reservations.connector.response.CityDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
public class CatalogConnector {

    private final HttpConnectorConfiguration configuration;

    private final String HOST = "api-catalog";
    private final String ENDPOINT = "get-city";

    @Autowired
    public CatalogConnector(HttpConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    @CircuitBreaker(name = "api-catalog")
    public CityDTO getCity(String code) {

        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        EndpointConfiguration endpointConfiguration = hostConfiguration.getEndpoints().get(ENDPOINT);

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(endpointConfiguration.getConnectionTimeout()))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(endpointConfiguration.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(endpointConfiguration.getWriteTimeout(), TimeUnit.MILLISECONDS)));

        WebClient webClient = WebClient.builder()
                .baseUrl(endpointConfiguration.getProtocol() + "://" + hostConfiguration.getHost() + ":" + hostConfiguration.getPort() + endpointConfiguration.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        return webClient.get().uri(uriBuilder -> uriBuilder.build(code)) //Construye la URI
                .retrieve()//Hace la llamada
                .bodyToMono(CityDTO.class)//Un solo elemento, si fuera una lista sería bodyToFlux
                .share() // Sirve para compartir el resultado de la llamada con varios suscriptores
                .block(); // Sirve para bloquear la ejecución hasta que se obtenga el resultado (sincrónico) si fuera asincrónico se usaría subscribe
    }
}
