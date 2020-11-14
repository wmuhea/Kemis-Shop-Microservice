package com.kemisshop.orderservice.mapper;

import com.kemisshop.orderservice.domain.Cart;
import com.kemisshop.orderservice.domain.CartItem;
import com.kemisshop.orderservice.domain.Order;
import com.kemisshop.orderservice.dto.CartDto;
import com.kemisshop.orderservice.dto.ItemDto;
import com.kemisshop.orderservice.dto.OrderDto;
import com.kemisshop.orderservice.dto.ResponseBean;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;


@Mapper(componentModel = "spring")
public interface DtoEntityMapper {

    Cart toEntity(CartDto cartDto);

    CartDto toDto(Cart cart);

    CartItem toEntity(ItemDto itemDto);

    ItemDto toDto(CartItem cartItem);

    OrderDto toDto(Order order);

   default ResponseBean toResponseBean(HttpStatus responseStatus, Object responsePayLoad) {
       return new ResponseBean(responseStatus.toString(), responsePayLoad);
   }

}
