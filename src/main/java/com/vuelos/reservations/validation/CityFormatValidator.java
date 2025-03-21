package com.vuelos.reservations.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CityFormatValidator implements ConstraintValidator <CityFormatConstraint, String> {

    @Override
    public void initialize(CityFormatConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String campo, ConstraintValidatorContext constraintValidatorContext) {
        return campo != null && campo.length() == 3 && campo.matches("[A-Z]");
    }
}
