package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;

public interface AccountFactory {
     String appliesTo();
     Account getAccount(AccountForm registrationForm, AccountType accountType);


}

