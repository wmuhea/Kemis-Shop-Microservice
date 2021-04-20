package com.kemisshop.accountservice.app.service;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import com.kemisshop.accountservice.app.port.in.AccountRegistrationOrUpdateServiceAccessPort;
import com.kemisshop.accountservice.app.port.out.LoadAccountInfoPort;
import com.kemisshop.accountservice.app.port.out.CreateAccountPort;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import com.kemisshop.accountservice.factory.AccountFactory;
import com.kemisshop.accountservice.factory.AccountResponseFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
    wontgn created on 1/8/21 inside the package - com.kemisshop.accountservice.app
*/
@Service
public class AccountRegistrationOrUpdateService implements
        AccountRegistrationOrUpdateServiceAccessPort {

    private final CreateAccountPort accountRegisterOrUpdater;
    private final LoadAccountInfoPort accountInformationLoader;
    private final List<AccountFactory> accountFactories;
    private final List<AccountResponseFactory> accountResponseFactories;

    private Map<String, AccountFactory>
            accountFactoriesMap = new HashMap<>();
    private Map<Class<? extends Account>, AccountResponseFactory>
            accountResponseFactoriesMap = new HashMap<>();

    public AccountRegistrationOrUpdateService(
            CreateAccountPort accountRegisterOrUpdater,
            LoadAccountInfoPort accountInformationLoader,
            List<AccountFactory> accountFactories,
            List<AccountResponseFactory> accountResponseFactories) {

        this.accountRegisterOrUpdater = accountRegisterOrUpdater;
        this.accountInformationLoader = accountInformationLoader;
        this.accountFactories = accountFactories;
        this.accountResponseFactories = accountResponseFactories;
    }

    /**
     * Initializes the factories Map
     */
    @PostConstruct
    public void initResponseMap() {
      accountResponseFactories
              .forEach(
                      accountResponseFactory -> accountResponseFactoriesMap
                      .put(accountResponseFactory.appliesTo(), accountResponseFactory)
              );

        accountFactories
                .forEach(accountFactory -> accountFactoriesMap
                        .put(accountFactory.appliesTo(), accountFactory)
                );
    }
    /**
     *
     * @param registrationForm
     * @return
     */
    @Override
    public AccountResponse register(AccountForm registrationForm) {

        String typeOfAccount = registrationForm.getAccountType();
        Role role = Role.findByLabel(typeOfAccount);
        AccountType accountType =
                accountInformationLoader.loadAccountTypeByRole(role);

        AccountFactory accountFactory = accountFactoriesMap.get(typeOfAccount);
        Account newAccount = accountFactory.getAccount(registrationForm, accountType);
        accountRegisterOrUpdater.save(newAccount);

        return accountResponseFactoriesMap.get(newAccount.getClass())
                .getAccountResponse(newAccount);
    }

    /**
     * This account update is possible only for changing
     * email address and password
     * @param registrationForm
     * @param publicAccountId
     * @return
     */
    @Override
    public AccountResponse update(AccountForm registrationForm, UUID publicAccountId) {
        Account accountFromDb =
                accountInformationLoader.loadAccountByPublicId(publicAccountId);
        accountFromDb.setFullName(registrationForm.getFullName());
        accountFromDb.setEmail(registrationForm.getEmail());
        accountFromDb.setPassword(registrationForm.getPassword());
        accountFromDb.setPhoneNumber(registrationForm.getPhoneNumber());
        accountRegisterOrUpdater.save(accountFromDb);

        return accountResponseFactoriesMap.get(accountFromDb.getClass())
                .getAccountResponse(accountFromDb);
    }


}
