package com.kemisshop.accountservice.app.port.out;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import org.springframework.data.domain.Page;

import java.util.UUID;

/*
    wontgn created on 1/8/21 inside the package - com.kemisshop.accountservice.app.port.out
*/
public interface LoadAccountInfoPort {

    Account loadAccountByPublicId(UUID accountPublicId);
    Page<Account> loadAccountsByType(AccountType accountType);
    Page<Account> loadNewSellerAccounts();
    AccountType loadAccountTypeByRole(Role role);
    Page<AccountType> loadAllAccountTypes(int page, int size);
}
