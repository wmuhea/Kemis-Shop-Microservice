package com.kemisshop.catalogservice.clients;

/*
    wontgn created on 11/27/20 inside the package - com.kemisshop.catalogservice.clients
    This class defines fall back methods for the feign client method used to check
    if a product is ordered or not by calling the order service, before a product
    is deleted by a Seller.

    It is possible also to create a class that implements FallBackFactory<OrderServiceFeignClient>
    that returns an instance of a fallback class which is defined alike this class.

*/

import com.kemisshop.catalogservice.dto.ResponseBean;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("orderservice")
public class OrderServiceFeignClientFallback implements OrderServiceFeignClient{
    @Autowired
    DtoEntityMapper mapper;

    @Override
    public ResponseBean isProductOrdered(UUID sellerPublicId, UUID productPublicId) {
        String responsePayLoadForDegradedOrderService = "It is not know if the product is ordered or not";
        return mapper.toResponseBean(HttpStatus.GATEWAY_TIMEOUT, responsePayLoadForDegradedOrderService);
    }
}
