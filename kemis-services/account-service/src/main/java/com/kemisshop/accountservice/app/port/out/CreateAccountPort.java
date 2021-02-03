package com.kemisshop.accountservice.app.port.out;

import com.kemisshop.accountservice.app.model.Account;

public interface CreateAccountPort {

    Account save(Account account);

}
