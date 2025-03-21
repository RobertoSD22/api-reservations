package com.vuelos.reservations.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class ReservationDTO {

    private Long id;

    @Valid //Sirve para validar los atributos de los elementos de la lista (Nota: Si no se pone, no se validan)
    @NotEmpty(message = "La lista de pasajeros no puede estar vacía")
    private List<PassengerDTO> passagers;

    @Valid
    private ItineraryDTO itinerary;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PassengerDTO> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<PassengerDTO> passagers) {
        this.passagers = passagers;
    }

    public ItineraryDTO getItinerary() {
        return itinerary;
    }

    public void setItinerary(ItineraryDTO itinerary) {
        this.itinerary = itinerary;
    }
}
