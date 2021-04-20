package com.kemisshop.catalogservice.adapter.external;

import com.kemisshop.catalogservice.mapper.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/*
    wontgn created on 11/19/20 inside the package - com.kemisshop.catalogservice.clients
*/
@FeignClient(name = "orderservice",
        value = "orderservice",
        fallback = OrderServiceFeignClientFallback.class
)
public interface OrderServiceFeignClient {



    @RequestMapping(
            value = "/order/{sId}/product",
            method = RequestMethod.GET,
            consumes="application/json"
    )

    ResponseBean isProductOrdered(
            @PathVariable("sId") UUID sellerPublicId,
            @RequestParam("pId") UUID productPublicId
    );



}
