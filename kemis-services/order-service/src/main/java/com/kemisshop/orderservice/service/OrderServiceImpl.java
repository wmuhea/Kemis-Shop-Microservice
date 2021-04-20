package com.kemisshop.orderservice.service;

import com.kemisshop.orderservice.domain.Cart;
import com.kemisshop.orderservice.domain.CartItem;
import com.kemisshop.orderservice.domain.Order;
import com.kemisshop.orderservice.domain.OrderStatus;
import com.kemisshop.orderservice.dto.CartDto;
import com.kemisshop.orderservice.dto.OrderDto;
import com.kemisshop.orderservice.mapper.DtoEntityMapper;
import com.kemisshop.orderservice.repository.CartRepository;
import com.kemisshop.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final DtoEntityMapper mapper;
    private final DecimalFormat twoDigitFormatterOfPrice;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            DtoEntityMapper mapper
    ) {

        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
        this.twoDigitFormatterOfPrice = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    }

    @Override
    public OrderDto save(Order order) {
        return mapper.toDto(orderRepository.save(order));
    }

    @Override
    public Boolean isProductOrdered(UUID publicSellerId, UUID publicProductId) {

       return orderRepository
               .checkIfProductIsOrdered(publicSellerId, publicProductId)
               .isPresent();
    }

    @Override
    public List<OrderDto> getBuyerOrderHistory(UUID publicBuyerId) {
        return orderRepository
                .findOrdersByBuyerId(publicBuyerId)
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    @Override
    public List<OrderDto> getAllNewOrdersForSeller(UUID publicSellerId) {

        return orderRepository.findBySellerId(publicSellerId)
                .stream().map(mapper::toDto)
                .collect(toList());

    }

    @Override
    public String cancelOrderBySeller( UUID orderPublicId) {
        Order order = orderRepository.findByOrderPublicIdAndSellerId(orderPublicId);
        OrderStatus cancelStatus = OrderStatus.findByLabel("Cancelled By Seller");
        order.setOrderStatus(cancelStatus);
        orderRepository.save(order);
        return "Order cancelled";
    }

    @Override
    public CartDto save(Cart cart) {

        return mapper.toDto(cartRepository.save(cart));
    }

    @Override
    public CartDto getCartForBuyer(UUID publicBuyerId) {

       Cart buyerCart =
                cartRepository.findByBuyerId(publicBuyerId)
                        .orElseGet(() -> {
                        Cart newBuyerCart = Cart.builder()
                                .publicCartId(UUID.randomUUID())
                                .buyerPublicId(publicBuyerId)
                                .build();
                        return cartRepository.save(newBuyerCart);
        });
        return mapper.toDto(buyerCart);
    }

    @Override
    @Transactional
    public String checkOutOrder(Cart cart) {
        // first collect each cart Item to a map based on a seller Id
       groupCartBySellerID(cart).forEach(orderRepository::save);
       return "Your order is Success full placed";
    }
    // Maps a cart to list of orders to different sellers
    private List<Order> groupCartBySellerID(Cart cart) {
        Map<UUID, Set<CartItem>> orderMap = cart.getCartItemSet().stream()
                .collect(groupingBy(CartItem::getSellerPublicId, toSet()));
        List<Order> ordersList = new ArrayList<>();
        // group each order save them all
        orderMap.forEach((selleId,items) -> {

            BigDecimal total = getTotalPrice(items);
            UUID buyerPublicId = cart.getBuyerPublicId();
            OrderStatus orderStatus = OrderStatus.findByLabel("Ordered");

            Order newOrder = new Order.OrderBuilder()
                    .withCartItems(items)
                    .withOrderStatus(orderStatus)
                    .withBuyerPublicId(buyerPublicId)
                    .withSellerPublicId(selleId)
                    .withGrandTotal(total)
                    .build();
            ordersList.add(newOrder);
        });
        return ordersList;
    }

    // Private map that calculates the grand total for all items that are ordered for a seller
    private BigDecimal getTotalPrice(Set<CartItem> items) {
        double totalOrderPrice = 0.0;
        for(CartItem item : items) {
            totalOrderPrice += item.getItemPrice().doubleValue();
        }

        String formattedTotalPrice = twoDigitFormatterOfPrice.format(totalOrderPrice);
        return BigDecimal.valueOf(Double.parseDouble(formattedTotalPrice));

    }
}
