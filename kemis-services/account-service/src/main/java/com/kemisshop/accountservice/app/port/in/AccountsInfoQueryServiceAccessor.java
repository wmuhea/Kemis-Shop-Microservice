package com.kemisshop.accountservice.app.port.in;

import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import com.kemisshop.accountservice.factory.AccountTypeResponse;
import org.springframework.data.domain.Page;


import java.util.UUID;

public interface AccountsInfoQueryServiceAccessor {
    AccountResponse getAccountByPublicId(UUID publicAccountId);
    Page<AccountResponse> getNewSellerAccounts();
    Page<AccountResponse> getAccountsOfType(String accountType);
    Page<AccountTypeResponse> getAllAccountTypes(int page, int size);

}
