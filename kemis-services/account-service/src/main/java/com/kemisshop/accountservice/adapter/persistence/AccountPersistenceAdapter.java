package com.kemisshop.accountservice.adapter.persistence;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import com.kemisshop.accountservice.app.port.out.CreateAccountPort;
import com.kemisshop.accountservice.app.port.out.LoadAccountInfoPort;
import com.kemisshop.accountservice.app.port.out.ManageAccountPort;
import com.kemisshop.accountservice.app.port.out.UpdateAccountStatePort;
import org.springframework.data.domain.Page;

import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.adapter.persistence
 */
public class AccountPersistenceAdapter implements CreateAccountPort,
        LoadAccountInfoPort, ManageAccountPort, UpdateAccountStatePort {
    
    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public AccountType save(AccountType accountType) {
        return null;
    }

    @Override
    public Account loadAccountByPublicId(UUID accountPublicId) {
        return null;
    }

    @Override
    public Page<Account> loadAccountsByType(AccountType accountType) {
        return null;
    }

    @Override
    public Page<Account> loadNewSellerAccounts() {
        return null;
    }

    @Override
    public AccountType loadAccountTypeByRole(Role role) {
        return null;
    }

    @Override
    public Page<AccountType> loadAllAccountTypes(int page, int size) {
        return null;
    }

    @Override
    public Account activateSellerAccount(UUID publicSellerId) {
        return null;
    }

    @Override
    public Account deactivateAccount(UUID publicSellerId) {
        return null;
    }

    @Override
    public AccountType createAccountType(AccountType accountType) {
        return null;
    }
}
