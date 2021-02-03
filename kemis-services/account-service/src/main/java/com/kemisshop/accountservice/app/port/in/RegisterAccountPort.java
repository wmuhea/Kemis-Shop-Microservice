package com.kemisshop.accountservice.app.port.in;

import com.kemisshop.accountservice.app.model.Account;

/*
    wontgn created on 1/8/21 inside the package - com.kemisshop.accountservice.app.port.in
*/
public interface RegisterAccountPort {

    Account register(Account account);
}
