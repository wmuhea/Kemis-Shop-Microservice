package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.SellerAccount;
import org.springframework.stereotype.Component;

/*
    wontgn created on 2/5/21 inside the package - com.kemisshop.accountservice.mapper
*/
@Component
public class SellerAccountFactory implements AccountFactory{

    @Override
    public String appliesTo() {
        return "Seller";
    }

    @Override
    public Account getAccount(AccountForm registrationForm, AccountType accountType) {
        return SellerAccount.of(
                registrationForm.getFullName(),
                registrationForm.getEmail(),
                registrationForm.getPassword(),
                registrationForm.getPhoneNumber(),
                accountType
        );
    }
}
