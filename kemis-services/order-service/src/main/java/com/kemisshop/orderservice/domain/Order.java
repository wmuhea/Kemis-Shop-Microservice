package com.kemisshop.orderservice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document
@Getter
@Setter
public class Order {

    @Id
    private String id;

    private Set<CartItem> cartItems;

    private UUID sellerPublicId;

    private UUID buyerPublicId;

    private OrderStatus orderStatus;

    private BigDecimal grandOrderTotal;

    public Order() {
    }

    private Order( OrderBuilder orderBuilder) {
        this.cartItems = orderBuilder.cartItems;
        this.sellerPublicId = orderBuilder.sellerPublicId;
        this.buyerPublicId = orderBuilder.buyerPublicId;
        this.orderStatus = orderBuilder.orderStatus;
        this.grandOrderTotal = orderBuilder.grandOrderTotal;
    }

    public static class OrderBuilder {
       private Set<CartItem> cartItems;

       private UUID sellerPublicId;

       private UUID buyerPublicId;

        private OrderStatus orderStatus;

        private BigDecimal grandOrderTotal;

        public OrderBuilder() {
        }

        public OrderBuilder withCartItems(Set<CartItem> items) {
            this.cartItems = new HashSet<>();
            items.forEach(item -> this.cartItems.add(item));
            return this;
        }

        public OrderBuilder withSellerPublicId(UUID sellerPublicId) {
            this.sellerPublicId = sellerPublicId;
            return this;
        }

        public OrderBuilder withBuyerPublicId(UUID buyerPublicId) {
            this.buyerPublicId = buyerPublicId;
            return this;
        }
        public OrderBuilder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
         }

        public OrderBuilder withGrandTotal(BigDecimal grandOrderTotal) {
            this.grandOrderTotal = grandOrderTotal;
            return this;
         }

        public Order build() {
           return new Order(this);
         }

    }
}
