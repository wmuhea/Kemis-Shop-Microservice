package com.kemisshop.orderservice.dto;

import com.kemisshop.orderservice.domain.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
public class OrderDto {

    private Set<ItemDto> items;

    private UUID sellerPublicId;

    private UUID buyerPublicId;

    private OrderStatus orderStatus;

    private BigDecimal grandOrderTotal;
}
