package com.kemisshop.orderservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(buyerPublicId, cart.buyerPublicId) &&
                Objects.equals(publicCartId, cart.publicCartId) &&
                Objects.equals(cartItemSet, cart.cartItemSet) &&
                Objects.equals(grandTotal, cart.grandTotal) &&
                Objects.equals(createdAt, cart.createdAt) &&
                Objects.equals(UpdatedAt, cart.UpdatedAt);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

