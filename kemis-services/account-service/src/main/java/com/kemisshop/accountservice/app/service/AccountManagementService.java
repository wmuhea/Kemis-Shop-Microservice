package com.kemisshop.accountservice.app.service;

import com.kemisshop.accountservice.app.dto.request.AccountTypeForm;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.port.in.AccountsManagementServiceAccessPort;
import com.kemisshop.accountservice.app.port.out.ManageAccountPort;
import com.kemisshop.accountservice.factory.AccountTypeResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

/*
    wontgn created on 2/4/21 inside the package - com.kemisshop.accountservice.app.service
*/
@Service
public class AccountManagementService implements
        AccountsManagementServiceAccessPort {

    private final ManageAccountPort managementOutPort;

    public AccountManagementService(ManageAccountPort managementOutPort) {
        this.managementOutPort = managementOutPort;
    }

    @Override
    public Boolean activateSellerAccount(UUID publicAccountId) {
        return null;
    }

    @Override
    public Boolean deactivateAccount(UUID publicAccountId) {
        return null;
    }


    @Override
    public AccountTypeResponse createAccountType(AccountTypeForm accountTypeForm) {

        AccountType type = AccountType.of(accountTypeForm);
        return AccountTypeResponse.of(
                managementOutPort.createAccountType(type)
        );
    }

}
