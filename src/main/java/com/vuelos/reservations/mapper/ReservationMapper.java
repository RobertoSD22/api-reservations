package com.vuelos.reservations.mapper;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "passengers", source = "passengers")
    @Mapping(target = "itinerary", source = "itinerary")
    ReservationDTO toReservationDTO(Reservation reservation);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "passengers", source = "passengers")
    @Mapping(target = "itinerary", source = "itinerary")
    Reservation toReservation(ReservationDTO reservationDTO);
}