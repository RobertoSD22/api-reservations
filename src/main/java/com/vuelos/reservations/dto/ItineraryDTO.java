package com.vuelos.reservations.dto;

import jakarta.validation.Valid;

import java.util.List;

public class ItineraryDTO {

    @Valid
    private List<SegmentDTO> segments;

    private PriceDTO price;


    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }

    public List<SegmentDTO> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentDTO> segments) {
        this.segments = segments;
    }
}
