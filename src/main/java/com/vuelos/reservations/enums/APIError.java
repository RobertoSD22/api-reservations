package com.vuelos.reservations.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum APIError {

    COMMUNICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error de comunicación con el servicio"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Los atributos de la petición no son válidos"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reserva no encontrada"),
    RESERVATION_WITH_SAME_ID(HttpStatus.CONFLICT, "Ya existe una reserva con el mismo ID"),
    CITY_ORIGIN_NOT_FOUND(HttpStatus.BAD_REQUEST, "La ciudad origen enviada no existe"),
    CITY_DESTINATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "La ciudad destino enviada no existe"),
    LIMIT_EXCEEDED_REQUEST(HttpStatus.TOO_MANY_REQUESTS, "Se ha excedido el límite de peticiones");

    private final HttpStatus httpStatus;

    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
