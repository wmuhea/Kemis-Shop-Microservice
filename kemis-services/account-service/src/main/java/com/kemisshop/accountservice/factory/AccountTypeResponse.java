package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.model.AccountType;

import java.util.UUID;

/*
    wontgn created on 2/12/21 inside the package - com.kemisshop.accountservice.mapper
*/
public class AccountTypeResponse {

    private UUID publicAccountTypeId;
    private String typeOfAccount;

    public AccountTypeResponse() {
    }

    private AccountTypeResponse(UUID publicAccountTypeId, String typeOfAccount) {
        this.publicAccountTypeId = publicAccountTypeId;
        this.typeOfAccount = typeOfAccount;
    }

    public static AccountTypeResponse of(AccountType accountType) {

        return new AccountTypeResponse(
                accountType.getPublicAccountTypeId(),
                accountType.getRole().name()
        );
    }
}
