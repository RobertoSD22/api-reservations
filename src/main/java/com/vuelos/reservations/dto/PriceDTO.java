package com.vuelos.reservations.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceDTO {

    private BigDecimal totalPrice;

    private BigDecimal basePrice;

    private BigDecimal totalTax;

}
