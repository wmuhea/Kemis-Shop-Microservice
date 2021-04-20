package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.BuyerAccount;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import com.kemisshop.accountservice.app.dto.response.BuyerAccountResponse;
import org.springframework.stereotype.Component;


/*
    wontgn created on 2/4/21 inside the package - com.kemisshop.accountservice.mapper
*/
@Component
public class BuyerAccountResponseFactory implements AccountResponseFactory{

    @Override
    public Class<? extends Account> appliesTo() {
        return BuyerAccount.class;
    }

    @Override
    public AccountResponse getAccountResponse(Account account) {
        return BuyerAccountResponse.of(account);
    }
}
