package com.vuelos.reservations.service;

import com.vuelos.reservations.connector.CatalogConnector;
import com.vuelos.reservations.connector.response.CityDTO;
import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.dto.SegmentDTO;
import com.vuelos.reservations.enums.APIError;
import com.vuelos.reservations.exception.ReservationException;
import com.vuelos.reservations.model.Reservation;
import com.vuelos.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private final ConversionService conversionService;
    private final ReservationRepository repository;
    private final CatalogConnector catalogConnector;

    @Autowired
    public ReservationService(
            ConversionService conversionService,
            ReservationRepository repository, CatalogConnector catalogConnector) {
        this.conversionService = conversionService;
        this.repository = repository;
        this.catalogConnector = catalogConnector;
    }

    public List<ReservationDTO> getReservations() {
        return conversionService.convert(repository.getReservations(), List.class);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id);
        if (result.isEmpty()) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        return conversionService.convert(result.get(), ReservationDTO.class);
    }

    public ReservationDTO save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {
            throw new ReservationException(APIError.RESERVATION_WITH_SAME_ID);
        }

        this.checkCity(reservation);

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if (getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }

        this.checkCity(reservation);

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.update(id, Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public void delete(Long id) {
        if (getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        repository.delete(id);
    }

    private void checkCity(ReservationDTO reservationDTO) {
        for (SegmentDTO segmentDTO : reservationDTO.getItinerary().getSegments()) {
            CityDTO origin = catalogConnector.getCity(segmentDTO.getOrigin());

            if (Objects.isNull(origin)) {
                throw new ReservationException(APIError.CITY_ORIGIN_NOT_FOUND);
            }

            CityDTO destination = catalogConnector.getCity(segmentDTO.getDestination());

            if (Objects.isNull(destination)) {
                throw new ReservationException(APIError.CITY_DESTINATION_NOT_FOUND);
            }

            System.out.println("Origin: " + origin.getName() + " Destination: " + destination.getName());
        }
    }
}