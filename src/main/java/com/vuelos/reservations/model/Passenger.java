package com.vuelos.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Passenger {

    private Long id;

    private String firstName;

    private String lastName;

    private String documentType;

    private String documentNumber;

    private LocalDate birthday;

}
