package com.kemisshop.accountservice.app.port.out;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;

import java.util.UUID;

/*
    wontgn created on 2/4/21 inside the package - com.kemisshop.accountservice.app.port.out
*/
public interface ManageAccountPort {

    Account activateSellerAccount(UUID publicSellerId);
    Account deactivateAccount(UUID publicSellerId);
    AccountType createAccountType(AccountType accountType);

}
