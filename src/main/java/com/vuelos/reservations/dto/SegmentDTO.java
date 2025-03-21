package com.vuelos.reservations.dto;

import com.vuelos.reservations.validation.CityFormatConstraint;

public class SegmentDTO {

    @CityFormatConstraint // Esta anotación personalizada válida que el campo tenga 3 caracteres y sean letras mayúsculas
    private String origin;

    @CityFormatConstraint // Esta anotación personalizada válida que el campo tenga 3 caracteres y sean letras mayúsculas
    private String destination;

    private String departure;

    private String arrival;

    private String carrier;


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
