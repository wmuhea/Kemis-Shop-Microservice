package com.kemisshop.orderservice.api;

import com.kemisshop.orderservice.domain.Cart;
import com.kemisshop.orderservice.dto.CartDto;
import com.kemisshop.orderservice.dto.ResponseBean;
import com.kemisshop.orderservice.mapper.DtoEntityMapper;
import com.kemisshop.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
public class OrderApi {

    private final OrderService orderService;
    private final DtoEntityMapper mapper;

    public OrderApi(OrderService orderService, DtoEntityMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping("/{sId}")
    public ResponseBean getAllOrdersForSeller(
            @PathVariable("sId") UUID publicSellerId
    )
    {
       return mapper.toResponseBean(
               HttpStatus.OK, orderService.getAllNewOrdersForSeller(publicSellerId)
       );
    }

    @GetMapping("/{sId}/product")
    public ResponseBean isProductRecentlyOrdered(
            // The request parameter pId is the publicProductId
            @PathVariable("sId") UUID publicSellerId,
            @RequestParam(value="pId") UUID publicProductId
    ) {

        Boolean response =
                orderService.isProductOrdered(publicSellerId, publicProductId);
        return
                response ? mapper.toResponseBean(HttpStatus.FOUND, "Ordered") :
                mapper.toResponseBean(HttpStatus.NOT_FOUND, "Not Ordered");
    }

    @PutMapping
    public ResponseBean cancelOrder_by_seller(
            @RequestParam(value = "oId") UUID orderPublicId
    ){
        orderService.cancelOrderBySeller(orderPublicId);
        return mapper
                .toResponseBean(HttpStatus.ACCEPTED, "The order is Cancelled by the seller");
        // Here we have to send a message to the buyer that ordered the canceled order
    }

    @PostMapping
    public ResponseBean checkOutOrder(CartDto cartDto) {
        Cart cart = mapper.toEntity(cartDto);
        String response = orderService.checkOutOrder(cart);
        return mapper.toResponseBean(HttpStatus.ACCEPTED, response);
    }
}
