package com.kemisshop.orderservice.service;

import com.kemisshop.orderservice.domain.Cart;
import com.kemisshop.orderservice.domain.Order;
import com.kemisshop.orderservice.dto.CartDto;
import com.kemisshop.orderservice.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    // order methods
     OrderDto save(Order order);
     Boolean isProductOrdered(UUID publicSellerId, UUID publicProductId);
     List<OrderDto> getBuyerOrderHistory(UUID publicBuyerId);
     List<OrderDto> getAllNewOrdersForSeller(UUID publicSellerId);
     String cancelOrderBySeller(UUID orderPublicId);

    // Cart methods
     CartDto save(Cart cart);
     CartDto getCartForBuyer(UUID publicBuyerId);
     String checkOutOrder(Cart cart);



}
