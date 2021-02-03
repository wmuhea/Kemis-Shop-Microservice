package com.kemisshop.accountservice.app;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.port.in.RegisterAccountPort;
import com.kemisshop.accountservice.app.port.out.CreateAccountPort;

/*
    wontgn created on 1/8/21 inside the package - com.kemisshop.accountservice.app
*/
public class CreateAccountUseCaseImpl implements
        RegisterAccountPort {

    private final CreateAccountPort createAccountPort;

    public CreateAccountUseCaseImpl(CreateAccountPort createAccountPort) {
        this.createAccountPort = createAccountPort;
    }

    @Override
    public Account register(Account account) {
        return null;
    }
}
