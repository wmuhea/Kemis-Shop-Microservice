package com.kemisshop.accountservice.adapter.api;

import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import com.kemisshop.accountservice.app.port.in.AccountsInfoQueryServiceAccessor;
import com.kemisshop.accountservice.factory.AccountTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */
@RestController("/accounts")
public class AccountInfoQueryApi {

    private final AccountsInfoQueryServiceAccessor accountInfoLoader;

    public AccountInfoQueryApi(AccountsInfoQueryServiceAccessor accountInfoLoader) {
        this.accountInfoLoader = accountInfoLoader;
    }


    @GetMapping("/{acctPid}")
    public ResponseEntity<AccountResponse> getAccountInfo(
            @PathVariable("acctPid") UUID accountPublicId) {

        AccountResponse accountResponse =
                accountInfoLoader.getAccountByPublicId(accountPublicId);

        return new ResponseEntity<>(accountResponse, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> getAccountsOfType(
            @RequestParam("acctType") String accountType) {

        Page<AccountResponse> accountResponsePage =
                accountInfoLoader.getAccountsOfType(accountType);
        return new ResponseEntity<>(accountResponsePage, HttpStatus.FOUND);
    }

    @GetMapping("/types")
    public ResponseEntity<Page<AccountTypeResponse>> getAllAccountTypes(
            @RequestParam("p") int page,
            @RequestParam("s") int size) {

        Page<AccountTypeResponse> allAccountTypes =
                accountInfoLoader.getAllAccountTypes(page, size);
        return new ResponseEntity<>(allAccountTypes, HttpStatus.OK);

    }

    // This is the last update  git cherrypick for second time


}
