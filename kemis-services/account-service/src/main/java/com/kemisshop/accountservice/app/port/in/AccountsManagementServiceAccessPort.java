package com.kemisshop.accountservice.app.port.in;

import com.kemisshop.accountservice.app.dto.request.AccountTypeForm;
import com.kemisshop.accountservice.factory.AccountTypeResponse;

import java.util.UUID;

/*
    wontgn created on 1/6/21 inside the package - com.kemisshop.accountservice.app.port
*/
public interface AccountsManagementServiceAccessPort {

    Boolean activateSellerAccount(UUID publicAccountId);
    Boolean deactivateAccount(UUID publicAccountId);
    AccountTypeResponse createAccountType(AccountTypeForm accountTypeForm);

}
