package com.kemisshop.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String type;

    @JsonIgnore
    private UserProfileDto userProfile;

}
