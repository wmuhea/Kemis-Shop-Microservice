package com.kemisshop.accountservice.adapter.persistence;

import com.kemisshop.accountservice.adapter.persistence.repository.AccountRepo;
import com.kemisshop.accountservice.adapter.persistence.repository.AccountTypeRepo;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.SellerAccount;
import com.kemisshop.accountservice.app.port.out.ManageAccountPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
    wontgn created on 2/4/21 inside the package - com.kemisshop.accountservice.adapter.persistence
*/
@Component
public class ManageAccountPortAdapter implements ManageAccountPort {

    private final AccountRepo<? extends Account> accountRepo;
    private final AccountTypeRepo accountTypeRepository;

    public ManageAccountPortAdapter(
            AccountRepo<? extends Account> accountRepo,
            AccountTypeRepo accountTypeRepository) {

        this.accountRepo = accountRepo;
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public Account activateSellerAccount(UUID publicSellerId) {
        SellerAccount accountFromDb = accountRepo.findByPublicAccountId(publicSellerId);
        accountFromDb.approve();
       return  accountRepo.save(accountFromDb);
    }

    @Override
    public Account deactivateAccount(UUID publicSellerId) {
        return null;
    }

    @Override
    public AccountType createAccountType(AccountType accountType) {
       return accountTypeRepository.save(accountType);
    }
}
