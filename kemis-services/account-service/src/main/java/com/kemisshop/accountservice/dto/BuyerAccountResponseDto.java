package com.kemisshop.accountservice.dto;



import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class BuyerAccountResponseDto extends AccountResponseDto {

    private Long points;

    private Set<SellerAccountResponseDto> favoriteSellers;

    public BuyerAccountResponseDto() {
        favoriteSellers = new HashSet<>();
    }
}
