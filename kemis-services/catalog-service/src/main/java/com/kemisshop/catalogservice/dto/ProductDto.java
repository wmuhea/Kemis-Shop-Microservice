package com.kemisshop.catalogservice.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductDto extends RepresentationModel<ProductDto> implements Serializable{

    private static final long serialVersionUID = -3866924695543888588L;

    @NotNull
    private UUID publicSellerId;

    private UUID publicProductId;

    @NotBlank
    @Size(min = 4, max = 10)
    private String name;

    @Digits(integer= 5, fraction = 2)
    private BigDecimal price;

    private String category;

    private String imageName;

    @NotNull
    private Integer unitsInStock;

    @NotBlank
    private String description;

}
