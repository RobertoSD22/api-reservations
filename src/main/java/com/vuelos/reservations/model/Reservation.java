package com.vuelos.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Reservation {

    private Long id;

    private List<Passenger> passengers;

    private Itinerary itinerary;

}
