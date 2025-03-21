package com.vuelos.reservations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PassengerDTO {

    @NotBlank(message = "El campo firstName no puede estar vacío")
    private String firstName;

    @NotBlank(message = "El campo lastName no puede estar vacío")
    private String lastName;

    private String documentType;

    private String documentNumber;

    @Past(message = "La fecha de nacimiento (birthday) debe ser anterior a la fecha actual")
    private LocalDate birthday;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
