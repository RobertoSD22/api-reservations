package com.vuelos.reservations.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // Esta anotación se incluirá en la documentación generada por JavaDoc
@Constraint(validatedBy = CityFormatValidator.class) // La clase que implementa la lógica de validación
@Target({ElementType.METHOD, ElementType.FIELD}) // Esta anotación se puede aplicar a métodos y campos
@Retention(RetentionPolicy.RUNTIME) // Esta anotación estará disponible en tiempo de ejecución
public @interface CityFormatConstraint {

    // Mensaje que se mostrará si la validación falla
    String message() default "Formato para city invalido";

    // Se pueden definir grupos de restricciones
    Class<?>[] groups() default {};

    // Se pueden definir payloads para pasar información adicional sobre el error
    Class<? extends Payload>[] payload() default {};
}
