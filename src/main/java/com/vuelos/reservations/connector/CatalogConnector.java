package com.vuelos.reservations.connector;

import com.vuelos.reservations.connector.response.CityDTO;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class CatalogConnector {

    public CityDTO getCity(String code) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:6070/api/flights/catalog/city/{code}")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)))
                .build();

        return webClient.get().uri(uriBuilder -> uriBuilder.build(code)) //Construye la URI
                .retrieve()//Hace la llamada
                .bodyToMono(CityDTO.class)//Un solo elemento, si fuera una lista sería bodyToFlux
                .share() // Sirve para compartir el resultado de la llamada con varios suscriptores
                .block(); // Sirve para bloquear la ejecución hasta que se obtenga el resultado (sincrónico) si fuera asincrónico se usaría subscribe
    }
}
