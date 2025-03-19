package com.vuelos.reservations.dto;

import java.util.List;

public class ReservationDTO {

    private Long id;

    private List<PassagerDTO> passagers;

    private ItineraryDTO itinerary;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PassagerDTO> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<PassagerDTO> passagers) {
        this.passagers = passagers;
    }

    public ItineraryDTO getItinerary() {
        return itinerary;
    }

    public void setItinerary(ItineraryDTO itinerary) {
        this.itinerary = itinerary;
    }
}
