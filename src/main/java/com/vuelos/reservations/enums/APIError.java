package com.vuelos.reservations.enums;

import org.springframework.http.HttpStatus;

public enum APIError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Los atributos de la petición no son válidos"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "El mensaje de la petición no es válido"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reserva no encontrada"),
    RESERVATION_WITH_SAME_ID(HttpStatus.CONFLICT, "Ya existe una reserva con el mismo ID");

    private final HttpStatus httpStatus;

    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
