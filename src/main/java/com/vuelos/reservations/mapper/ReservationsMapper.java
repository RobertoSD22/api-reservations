package com.vuelos.reservations.mapper;

import com.vuelos.reservations.dto.ReservationDTO;
import com.vuelos.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationsMapper extends Converter<List<Reservation>, List<ReservationDTO>>  {

    @Override
    public List<ReservationDTO> convert(List<Reservation> source);
}
