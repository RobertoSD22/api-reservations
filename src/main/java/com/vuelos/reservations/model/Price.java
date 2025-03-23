package com.vuelos.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Price {

    private Long id;

    private BigDecimal totalPrice;

    private BigDecimal basePrice;

    private BigDecimal totalTax;

}
