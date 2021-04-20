package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.SellerAccount;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import com.kemisshop.accountservice.app.dto.response.SellerAccountResponse;
import org.springframework.stereotype.Component;

/*
    wontgn created on 2/4/21 inside the package - com.kemisshop.accountservice.mapper
*/

@Component
public class SellerAccountResponseFactory implements AccountResponseFactory {
    @Override
    public Class<? extends Account> appliesTo() {
        return SellerAccount.class;
    }

    @Override
    public AccountResponse getAccountResponse(Account account) {
        return  SellerAccountResponse.of(account);
    }
}
