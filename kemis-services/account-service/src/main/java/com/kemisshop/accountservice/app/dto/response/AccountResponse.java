package com.kemisshop.accountservice.app.dto.response;

import com.kemisshop.accountservice.app.model.Account;

import java.util.UUID;

public abstract class AccountResponse {

    private String fullName;
    private UUID publicAccountId;
    private String email;
    private String phoneNUmber;

    public AccountResponse() {
    }

    public AccountResponse(Account account) {
        this.fullName = account.getFullName();
        this.publicAccountId = account.getPublicAccountId();
        this.email = account.getEmail();
        this.phoneNUmber = account.getPhoneNumber();
    }

}
