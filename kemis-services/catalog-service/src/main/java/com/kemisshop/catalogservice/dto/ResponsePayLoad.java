package com.kemisshop.catalogservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Builder
public class ResponsePayLoad {

    List<? extends Object> products = new ArrayList<>();
    List<? extends Object> ratings = new ArrayList<>();
    Integer numberOfPagesOfProduct = 0;




}
