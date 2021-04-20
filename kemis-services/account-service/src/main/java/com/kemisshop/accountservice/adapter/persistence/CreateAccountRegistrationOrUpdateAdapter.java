package com.kemisshop.accountservice.adapter.persistence;

import com.kemisshop.accountservice.adapter.persistence.repository.AccountRepo;
import com.kemisshop.accountservice.adapter.persistence.repository.AccountTypeRepo;
import com.kemisshop.accountservice.app.model.*;
import com.kemisshop.accountservice.app.port.out.CreateAccountPort;
import org.springframework.stereotype.Component;

/*
    wontgn created on 2/3/21 inside the package - com.kemisshop.accountservice.adapter.persistence
*/
@Component
public class CreateAccountRegistrationOrUpdateAdapter implements CreateAccountPort {

    /**
     * You may not need to inject all types of repo;
     */

    private final AccountTypeRepo accountTypeRepo;
    private final AccountRepo<? extends Account> accountRepo;

    public CreateAccountRegistrationOrUpdateAdapter(
            AccountTypeRepo accountTypeRepo,
            AccountRepo<? extends Account> accountRepo) {

        this.accountTypeRepo = accountTypeRepo;
        this.accountRepo = accountRepo;
    }


    @Override
    public Account save(Account account) {
        /*return repoMap.get(account.getAccountType())
                .save(account);*/
        return accountRepo
                .save(account);
    }


    @Override
    public AccountType save(AccountType accountType) {
        return accountTypeRepo.save(accountType);
    }


}
