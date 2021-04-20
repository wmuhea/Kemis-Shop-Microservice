package com.kemisshop.accountservice.adapter.api;

import com.kemisshop.accountservice.app.port.in.AccountsInfoQueryServiceAccessor;
import com.kemisshop.accountservice.app.port.in.AccountsManagementServiceAccessPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */
@RestController("/accounts/management")
public class AccountManagementApi {

    private final AccountsManagementServiceAccessPort accountManager;
    private final AccountsInfoQueryServiceAccessor accountInfoLoader;

    public AccountManagementApi(AccountsManagementServiceAccessPort accountManager,
                                AccountsInfoQueryServiceAccessor accountInfoLoader) {

        this.accountManager = accountManager;
        this.accountInfoLoader = accountInfoLoader;
    }

    @PatchMapping
    public ResponseEntity<Boolean> approveSeller(
            @RequestParam("sPid") UUID sellerPublicId) {

        return new ResponseEntity<Boolean>(
                accountManager.activateSellerAccount(sellerPublicId),
                HttpStatus.ACCEPTED
        );
    }



    /**
     *
     * @param accountPublicId
     * @return
     * Check this later as it is supposed to be used by only
     * admin users for deleting accounts marked for deletion
     * by the account owners
     */

    @DeleteMapping
    public ResponseEntity<String> deactivateAccount(
            @RequestParam("delAcctPid") UUID accountPublicId) {

        accountManager.deactivateAccount(accountPublicId);
        return new ResponseEntity<String>("The account is ready for deletion",
                HttpStatus.ACCEPTED
        );
    }
}
