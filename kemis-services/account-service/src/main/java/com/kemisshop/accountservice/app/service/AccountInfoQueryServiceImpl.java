package com.kemisshop.accountservice.app.service;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import com.kemisshop.accountservice.app.port.in.AccountsInfoQueryServiceAccessor;
import com.kemisshop.accountservice.app.port.out.LoadAccountInfoPort;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import com.kemisshop.accountservice.factory.AccountResponseFactory;
import com.kemisshop.accountservice.factory.AccountTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */
@Service
public class AccountInfoQueryServiceImpl implements
        AccountsInfoQueryServiceAccessor {

    private final LoadAccountInfoPort accountInfoQueryOutPort;
    private final List<AccountResponseFactory> accountResponseFactories;
    private Map<Class<? extends Account>, AccountResponseFactory>
            accountResponseFactoriesMap = new HashMap<>();

    public AccountInfoQueryServiceImpl(
            LoadAccountInfoPort accountInfoQueryOutPort,
            List<AccountResponseFactory> accountResponseFactories) {

        this.accountInfoQueryOutPort = accountInfoQueryOutPort;

        this.accountResponseFactories = accountResponseFactories;
    }


    /**
     * Initiate account response factory map
     */
    @PostConstruct
    public void initResponseFactoriesMap(){
        accountResponseFactories.forEach(
                responseFactory -> accountResponseFactoriesMap
                .put(responseFactory.appliesTo(), responseFactory)
        );
    }

    @Override
    public AccountResponse getAccountByPublicId(UUID publicAccountId) {
        Account accountFromDb = accountInfoQueryOutPort
                .loadAccountByPublicId(publicAccountId);

        return accountResponseFactoriesMap
                .get(accountFromDb.getAccountType())
                .getAccountResponse(accountFromDb);

    }

    @Override
    public Page<AccountResponse> getNewSellerAccounts() {

        Page<Account> accounts =
                accountInfoQueryOutPort.loadNewSellerAccounts();

        Page<AccountResponse> accountResponses =
                accounts.map(account -> {
                    AccountResponseFactory responseFactory =
                    accountResponseFactoriesMap.get(account.getClass());
                    return responseFactory.getAccountResponse(account);
                });

        return accountResponses;
    }

    /**
     * You do not need to hit the data base twice
     * just read the data needed once REFACTOR
     * @param accountType
     * @return
     */
    @Override
    public Page<AccountResponse> getAccountsOfType(String accountType) {

        AccountType accountTypeFromDb = accountInfoQueryOutPort
                .loadAccountTypeByRole(Role.findByLabel(accountType));

       return accountInfoQueryOutPort
               .loadAccountsByType(accountTypeFromDb)
               .map(sellerAccount -> accountResponseFactoriesMap.get(accountTypeFromDb)
                       .getAccountResponse(sellerAccount)
               );
    }

    @Override
    public Page<AccountTypeResponse> getAllAccountTypes(int page, int size) {
        return accountInfoQueryOutPort
                .loadAllAccountTypes(page, size)
                .map(AccountTypeResponse::of);
    }







}
