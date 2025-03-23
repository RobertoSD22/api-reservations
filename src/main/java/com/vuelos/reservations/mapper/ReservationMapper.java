package com.vuelos.reservations.mapper;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);
}