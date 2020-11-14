package com.kemisshop.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private UUID publicAccountId;

    private String email;

    private String password;

    private UserProfileDto userProfile;
}
