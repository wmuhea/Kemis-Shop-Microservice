package com.kemisshop.accountservice.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    @NotBlank
    private String fullName;

    
}
