package com.kemisshop.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class CartDto {

    private UUID buyerPublicId;

    private UUID publicCartId;

    private Set<ItemDto> itemSet;

    private BigDecimal cartTotal;

}
