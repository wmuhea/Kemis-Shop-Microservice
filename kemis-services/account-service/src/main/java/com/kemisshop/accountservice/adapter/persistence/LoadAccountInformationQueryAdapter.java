package com.kemisshop.accountservice.adapter.persistence;

import com.kemisshop.accountservice.adapter.persistence.repository.AccountRepo;
import com.kemisshop.accountservice.adapter.persistence.repository.AccountTypeRepo;
import com.kemisshop.accountservice.app.model.*;
import com.kemisshop.accountservice.app.port.out.LoadAccountInfoPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
    wontgn created on 2/3/21 inside the package - com.kemisshop.accountservice.adapter.persistence
*/
@Component
public class LoadAccountInformationQueryAdapter implements LoadAccountInfoPort {

    private final AccountTypeRepo accountTypeRepository;
    private final AccountRepo<? extends Account> accountsRepository;

    public LoadAccountInformationQueryAdapter(
            AccountTypeRepo accountTypeRepository,
            AccountRepo<? extends Account> accountsRepository) {

        this.accountTypeRepository = accountTypeRepository;
        this.accountsRepository = accountsRepository;
    }


    @Override
    public Account loadAccountByPublicId(UUID accountPublicId) {
        return accountsRepository
                .findByPublicAccountId(accountPublicId);
    }

    /**
     *
     * @param accountType
     * @return
     * default page size is used here to return 5 accounts per page
     * Update it to accept page latter
     */
    @Override
    public Page<Account> loadAccountsByType(AccountType accountType) {

        return accountsRepository
                .findByAccountTypeRole(PageRequest.of(0, 5), accountType.getRole());
    }

    @Override
    public Page<Account> loadNewSellerAccounts() {
        return accountsRepository
                .fetchUnapprovedSellerAccounts();
    }

    @Override
    public AccountType loadAccountTypeByRole(Role role) {
        return accountTypeRepository
                .findAccountTypeByRole(role);
    }

    @Override
    public Page<AccountType> loadAllAccountTypes(int page, int size) {
        return accountTypeRepository
                .findAll(PageRequest.of(page, size));
    }
}
