package com.vuelos.reservations.mapper;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationsMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "passengers", source = "passengers")
    @Mapping(target = "itinerary", source = "itinerary")
    List<ReservationDTO> toReservationDTOList(List<Reservation> reservations);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "passengers", source = "passengers")
    @Mapping(target = "itinerary", source = "itinerary")
    List<Reservation> toReservationList(List<ReservationDTO> reservationDTOs);
}