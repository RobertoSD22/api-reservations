package com.vuelos.reservations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassengerDTO {

    @NotBlank(message = "El campo firstName no puede estar vacío")
    private String firstName;

    @NotBlank(message = "El campo lastName no puede estar vacío")
    private String lastName;

    private String documentType;

    private String documentNumber;

    @Past(message = "La fecha de nacimiento (birthday) debe ser anterior a la fecha actual")
    private LocalDate birthday;

}
