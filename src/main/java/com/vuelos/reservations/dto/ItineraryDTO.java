package com.vuelos.reservations.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItineraryDTO {

    @Valid
    private List<SegmentDTO> segments;

    private PriceDTO price;

}
