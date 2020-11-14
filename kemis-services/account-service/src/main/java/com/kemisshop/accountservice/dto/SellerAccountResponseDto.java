package com.kemisshop.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerAccountResponseDto extends AccountResponseDto {

    private Boolean approved;

}
