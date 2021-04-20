package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.BuyerAccount;
import org.springframework.stereotype.Component;

/*
    wontgn created on 2/4/21 inside the package - com.kemisshop.accountservice.mapper
*/
@Component
public class BuyerAccountFactory implements AccountFactory{

    @Override
    public String appliesTo() {
        return "Buyer";
    }

    @Override
    public Account getAccount(AccountForm registrationForm, AccountType accountType) {

        return BuyerAccount.of(
                registrationForm.getFullName(),
                registrationForm.getEmail(),
                registrationForm.getPassword(),
                registrationForm.getPhoneNumber(),
                accountType
       );
    }
}
