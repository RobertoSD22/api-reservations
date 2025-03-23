package com.vuelos.reservations.controller;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.enums.APIError;
import com.vuelos.reservations.exception.ReservationException;
import com.vuelos.reservations.service.ReservationService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/reservations")
public class ReservationController implements ReservationResource {

    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<ReservationDTO> response = service.getReservations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id) {
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping
    @RateLimiter(name = "create-reservation", fallbackMethod = "createReservationFallback")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO response = service.save(reservationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @Min(1) @PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO response = service.update(id, reservationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@Min(1) @PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<ReservationDTO> createReservationFallback(
             @RequestBody ReservationDTO reservationDTO, RequestNotPermitted exception) {

        System.out.println("Fallback method for createReservation called");

        throw new ReservationException(APIError.LIMIT_EXCEEDED_REQUEST);
    }
}
