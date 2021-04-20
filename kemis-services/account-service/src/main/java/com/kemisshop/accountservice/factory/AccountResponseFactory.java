package com.kemisshop.accountservice.factory;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */
public interface AccountResponseFactory {

    Class<? extends Account> appliesTo();
    public AccountResponse getAccountResponse(Account account);
}
