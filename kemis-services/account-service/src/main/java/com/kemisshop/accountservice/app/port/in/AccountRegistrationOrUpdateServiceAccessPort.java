package com.kemisshop.accountservice.app.port.in;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;

import java.util.UUID;

/*
    wontgn created on 1/8/21 inside the package - com.kemisshop.accountservice.app.port.in
*/
public interface AccountRegistrationOrUpdateServiceAccessPort {
    AccountResponse register(AccountForm accountForm);
    AccountResponse update(AccountForm accountForm, UUID publicAccountId);

}

