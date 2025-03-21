package com.vuelos.reservations.exception;

import com.vuelos.reservations.dto.ErrorDTO;
import com.vuelos.reservations.enums.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<ErrorDTO> handleReservationException(ReservationException ex, WebRequest request) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDTO(ex.getDescription(), ex.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        List<String> reasons = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("%s - %s",error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(
                APIError.VALIDATION_ERROR.getHttpStatus()).body(
                        new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }
}
