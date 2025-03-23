package com.vuelos.reservations.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationDTO {

    private Long id;

    @Valid //Sirve para validar los atributos de los elementos de la lista (Nota: Si no se pone, no se validan)
    @NotEmpty(message = "La lista de pasajeros no puede estar vacía")
    private List<PassengerDTO> passengers;

    @Valid
    private ItineraryDTO itinerary;

}
