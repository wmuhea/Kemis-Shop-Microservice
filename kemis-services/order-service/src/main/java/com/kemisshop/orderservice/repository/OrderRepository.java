package com.kemisshop.orderservice.repository;

import com.kemisshop.orderservice.domain.Order;
import com.kemisshop.orderservice.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Order save(Order order) {
        return  mongoTemplate.save(order);
    }

    public List<Order> findBySellerId(UUID sellerPublicId) {

        Query query = new Query(Criteria.where("order.sellerPublicId")
                                .is(sellerPublicId).and("orderStatus").is("Ordered")
                    );

        return mongoTemplate.find(query, Order.class);
    }

    public Order findByOrderPublicIdAndSellerId(UUID orderPublicId) {

        Query query = new Query(
                Criteria.where("order.publicId")
                .is(orderPublicId)
        );

        return mongoTemplate.findOne(query, Order.class);
    }

    public Optional<Order> checkIfProductIsOrdered(UUID sellerPublicId, UUID productPublicId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("order.sellerPublicId")
                            .is(sellerPublicId)
                            .and("orderStatus")
                            .is(OrderStatus.Delivered).not());

        query.fields()
                .elemMatch("items", Criteria
                        .where("productPublicId")
                        .is(productPublicId)
                );

        return Optional.ofNullable(mongoTemplate.findOne(query, Order.class));
    }

    public List<Order> findOrdersByBuyerId(UUID buyerPublicId) {
        Query query = new Query(Criteria.where("buyerPublicId").is(buyerPublicId));
        return mongoTemplate.find(query, Order.class);

    }



}
