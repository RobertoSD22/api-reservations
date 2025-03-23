package com.vuelos.reservations.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorDTO {

    private String description;

    private List<String> reasons;


    public ErrorDTO(String description, List<String> reasons) {
        this.description = description;
        this.reasons = reasons;
    }
}
