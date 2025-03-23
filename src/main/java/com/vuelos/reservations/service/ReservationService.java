package com.vuelos.reservations.service;

import com.vuelos.reservations.connector.CatalogConnector;
import com.vuelos.reservations.connector.response.CityDTO;
import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.dto.SegmentDTO;
import com.vuelos.reservations.enums.APIError;
import com.vuelos.reservations.exception.ReservationException;
import com.vuelos.reservations.mapper.ReservationMapper;
import com.vuelos.reservations.mapper.ReservationsMapper;
import com.vuelos.reservations.model.Reservation;
import com.vuelos.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final ReservationsMapper reservationsMapper;
    private final ReservationRepository repository;
    private final CatalogConnector catalogConnector;

    @Autowired
    public ReservationService(
            ReservationMapper reservationMapper, ReservationsMapper reservationsMapper,
            ReservationRepository repository, CatalogConnector catalogConnector) {
        this.reservationMapper = reservationMapper;
        this.reservationsMapper = reservationsMapper;
        this.repository = repository;
        this.catalogConnector = catalogConnector;
    }

    public List<ReservationDTO> getReservations() {
        List<Reservation> reservations = repository.getReservations();
        return reservationsMapper.toReservationDTOList(reservations);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id);
        if (result.isEmpty()) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        return reservationMapper.toReservationDTO(result.get());
    }

    public ReservationDTO save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {
            throw new ReservationException(APIError.RESERVATION_WITH_SAME_ID);
        }

        this.checkCityt(reservation);

        Reservation transformed = reservationMapper.toReservation(reservation);
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        return reservationMapper.toReservationDTO(result);
    }

    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if (getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }

        this.checkCityt(reservation);

        Reservation transformed = reservationMapper.toReservation(reservation);
        Reservation result = repository.update(id, Objects.requireNonNull(transformed));
        return reservationMapper.toReservationDTO(result);
    }

    public void delete(Long id) {
        if (getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        repository.delete(id);
    }

    private void checkCityt(ReservationDTO reservationDTO) {
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