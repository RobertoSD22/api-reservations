package com.vuelos.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Itinerary {

    private Long id;

    private List<Segment> segments;

    private Price price;

}
