package com.vuelos.reservations.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum APIError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Los atributos de la petición no son válidos"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reserva no encontrada"),
    RESERVATION_WITH_SAME_ID(HttpStatus.CONFLICT, "Ya existe una reserva con el mismo ID"),
    CITY_ORIGIN_NOT_FOUND(HttpStatus.BAD_REQUEST, "La ciudad origen enviada no existe"),
    CITY_DESTINATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "La ciudad destino enviada no existe");

    private final HttpStatus httpStatus;

    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
