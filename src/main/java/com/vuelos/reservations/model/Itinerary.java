package com.vuelos.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Itinerary {

    private Long id;

    private List<Segment> segments;

    private Price price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
