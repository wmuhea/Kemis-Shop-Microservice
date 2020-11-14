package com.kemisshop.orderservice.api;

import com.kemisshop.orderservice.domain.Cart;
import com.kemisshop.orderservice.dto.CartDto;
import com.kemisshop.orderservice.dto.ResponseBean;
import com.kemisshop.orderservice.mapper.DtoEntityMapper;
import com.kemisshop.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderApi {

    private final OrderService orderService;
    private final DtoEntityMapper mapper;

    @Autowired
    public OrderApi(OrderService orderService, DtoEntityMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping("/cart")
    public ResponseBean saveCart(@RequestParam CartDto cartDto) {

         CartDto buyerCart = orderService.save(mapper.toEntity(cartDto));
         return    mapper.toResponseBean(HttpStatus.ACCEPTED,buyerCart);
    }

    @GetMapping("/cart")
    public ResponseBean getCartForBuyer(@RequestParam("id") UUID buyerPublicId) {

        CartDto buyerCart = orderService.getCartForBuyer(buyerPublicId);
        return mapper.toResponseBean(HttpStatus.CREATED, buyerCart);
    }

    @GetMapping("/{sellerId}/product")
    public ResponseBean isProductRecentlyOrdered(@PathVariable("sellerId") UUID sellerPublicId,
                                                   @RequestParam(value="id") UUID publicProductId) {

        Boolean response = orderService.isProductOrdered(sellerPublicId, publicProductId);
        ResponseBean responseBean = response ? mapper.toResponseBean(HttpStatus.FOUND, "Ordered") :
                mapper.toResponseBean(HttpStatus.NOT_FOUND, "Not Ordered");
        return responseBean;
    }

    @PutMapping
    public ResponseBean cancelOrder_by_seller(@RequestParam(value = "id") UUID orderPublicId){
        String response = orderService.cancelOrderBySeller(orderPublicId);
        return mapper.toResponseBean(HttpStatus.ACCEPTED, "The order is Cancelled");
        // Here we have to send a message to the buyer that ordered the canceled order
    }

    @PostMapping
    public ResponseBean checkOutOrder(CartDto cartDto) {
        Cart cart = mapper.toEntity(cartDto);
        String response = orderService.checkOutOrder(cart);
        return mapper.toResponseBean(HttpStatus.ACCEPTED, response);
    }
}
