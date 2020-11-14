package com.kemisshop.orderservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document
@Setter
@Getter
@Builder
public class Cart {

    @Id
    private String id;

    private UUID buyerPublicId;

    private UUID publicCartId;

    private Set<CartItem> cartItemSet;

    private BigDecimal grandTotal;

    private Date createdAt;

    private Date UpdatedAt;


    public Cart() {
    }

    public Cart(UUID buyerPublicId, UUID publicCartId, BigDecimal grandTotal, Date createdAt, Date updatedAt) {
        this.buyerPublicId = buyerPublicId;
        this.publicCartId = publicCartId;
        this.cartItemSet = new HashSet<>();
        this.grandTotal = grandTotal;
        this.createdAt = createdAt;
        UpdatedAt = updatedAt;
    }

    public Cart(String id, UUID buyerPublicId, UUID publicCartId, Set<CartItem> cartItemSet, BigDecimal grandTotal, Date createdAt, Date updatedAt) {
        this.id = id;
        this.buyerPublicId = buyerPublicId;
        this.publicCartId = publicCartId;
        this.cartItemSet = cartItemSet;
        this.grandTotal = grandTotal;
        this.createdAt = createdAt;
        UpdatedAt = updatedAt;
    }
}

