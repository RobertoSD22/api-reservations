package com.vuelos.reservations.dto;

import com.vuelos.reservations.validation.CityFormatConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SegmentDTO {

    @CityFormatConstraint // Esta anotación personalizada válida que el campo tenga 3 caracteres y sean letras mayúsculas
    private String origin;

    @CityFormatConstraint // Esta anotación personalizada válida que el campo tenga 3 caracteres y sean letras mayúsculas
    private String destination;

    private String departure;

    private String arrival;

    private String carrier;

}
