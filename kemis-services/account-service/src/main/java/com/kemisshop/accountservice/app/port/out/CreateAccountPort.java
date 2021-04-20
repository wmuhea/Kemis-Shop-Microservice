package com.kemisshop.accountservice.app.port.out;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;


public interface CreateAccountPort {

    Account save(Account account);
    AccountType save(AccountType accountType);

}
