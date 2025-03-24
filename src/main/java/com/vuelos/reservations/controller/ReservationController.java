package com.vuelos.reservations.controller;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.enums.APIError;
import com.vuelos.reservations.exception.ReservationException;
import com.vuelos.reservations.service.ReservationService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.List;

@Validated
@RestController
@RequestMapping("/reservations")
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationResource.class);

    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        LOGGER.info("Inicia la búsqueda de todas las reservas");
        List<ReservationDTO> response = service.getReservations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id) {
        LOGGER.info("Inicia la búsqueda de la reserva con id: {}", id);
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping
    @RateLimiter(name = "create-reservation", fallbackMethod = "createReservationFallback")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        LOGGER.info("Inicia la creación de una nueva reserva");
        ReservationDTO response = service.save(reservationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @Min(1) @PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDTO) {
        LOGGER.info("Inicia la actualización la reserva con id: {}", id);
        ReservationDTO response = service.update(id, reservationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@Min(1) @PathVariable Long id) {
        LOGGER.info("Inicia la eliminación de la reserva con id: {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        for (var violation : ex.getConstraintViolations()) {
            if ("updateReservation.id".equals(violation.getPropertyPath().toString()) &&
                    violation.getConstraintDescriptor().getAnnotation().annotationType().equals(Min.class)) {
                return ResponseEntity.badRequest().body("El ID debe ser mayor a 1.");
            }
        }
        return ResponseEntity.badRequest().body("Error de validación en la solicitud.");
    }

    private ResponseEntity<ReservationDTO> createReservationFallback(
             @RequestBody ReservationDTO reservationDTO, RequestNotPermitted exception) {

        LOGGER.debug("Fallback method for createReservation called");

        throw new ReservationException(APIError.LIMIT_EXCEEDED_REQUEST);
    }
}
