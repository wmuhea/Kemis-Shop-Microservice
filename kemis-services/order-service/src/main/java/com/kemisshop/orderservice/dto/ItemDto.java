package com.kemisshop.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class ItemDto {

    private UUID publicProductId;

    private Integer quantity;

    private BigDecimal itemPrice;

}
