package com.vuelos.reservations.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CityFormatValidator implements ConstraintValidator <CityFormatConstraint, String> {

    @Override
    public void initialize(CityFormatConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String campo, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(campo) && campo.matches("^[A-Z]{3}$");
    }
}
