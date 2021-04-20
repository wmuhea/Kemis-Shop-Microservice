package com.kemisshop.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private UUID publicProductId;

    private Integer quantity;

    private UUID sellerPublicId;

    private BigDecimal itemPrice;

    //Git cherry-pick third test

}
