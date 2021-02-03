package com.kemisshop.orderservice.api;

import com.kemisshop.orderservice.dto.CartDto;
import com.kemisshop.orderservice.dto.ResponseBean;
import com.kemisshop.orderservice.mapper.DtoEntityMapper;
import com.kemisshop.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@RequestMapping("/cart")
public class CartApi {

    private final OrderService orderService;
    private final DtoEntityMapper mapper;

    public CartApi(OrderService orderService, DtoEntityMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }


    /**
     * Invocked when the client wants to automatically save the cart
     * Saves the cart once in some time of interval
     */
    @PostMapping("/cart")
    public ResponseBean saveCart(@RequestParam CartDto cartDto) {

        CartDto buyerCart = orderService.save(mapper.toEntity(cartDto));
        return  mapper.toResponseBean(HttpStatus.ACCEPTED,buyerCart);
    }

    @GetMapping("/cart")
    public ResponseBean getCartForBuyer(
            @RequestParam("id") UUID buyerPublicId
    ) {

        CartDto buyerCart = orderService.getCartForBuyer(buyerPublicId);
        return mapper.toResponseBean(HttpStatus.CREATED, buyerCart);
    }
}
