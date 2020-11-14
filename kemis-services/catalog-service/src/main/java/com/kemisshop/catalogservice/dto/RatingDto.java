package com.kemisshop.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private  UUID ratingPublicId;

    @NotBlank
    private UUID buyerPublicId;

    private UUID productPublicId;

    @NotNull
    private Integer rating;

    @NotBlank
    private String review;


}
