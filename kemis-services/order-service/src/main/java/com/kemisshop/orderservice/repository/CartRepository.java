package com.kemisshop.orderservice.repository;

import com.kemisshop.orderservice.domain.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CartRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    // it is an upsert function
    public Cart save(Cart cart) {
       return mongoTemplate.save(cart);
    }

    public Optional<Cart> findByBuyerId(UUID buyerPublicId) {
        Query query = new Query(Criteria.where("cart.buyerPublicId")
                                .is(buyerPublicId)
                            );
        return Optional.ofNullable(mongoTemplate.findOne(query, Cart.class));
    }

    public Boolean delete(UUID buyerPublicId) {
        Query query = new Query(Criteria.where("buyerPublicId").is(buyerPublicId));

        return mongoTemplate.remove(query, Cart.class).wasAcknowledged();
    }
}
