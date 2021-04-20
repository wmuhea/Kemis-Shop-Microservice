package com.kemisshop.accountservice.adapter.api;

import com.kemisshop.accountservice.app.dto.request.AccountForm;
import com.kemisshop.accountservice.app.port.in.AccountRegistrationOrUpdateServiceAccessPort;
import com.kemisshop.accountservice.app.dto.response.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */
@RestController("/accounts")
public class AccountRegistrationOrUpdateApi {

    private final AccountRegistrationOrUpdateServiceAccessPort accountModifier;

    public AccountRegistrationOrUpdateApi(
            AccountRegistrationOrUpdateServiceAccessPort accountModifier
    ) {

        this.accountModifier = accountModifier;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Validated @RequestBody AccountForm accountForm) {

        AccountResponse createdAccount = accountModifier.register(accountForm);
        return new ResponseEntity<>(createdAccount,HttpStatus.CREATED);
    }

    /**
     * Think about what you will render for update
     * @param accountForm this is account form which is the request dto
     * @param accountPublicId account public Id which is UUID
     * @return returns updated account information in response type
     */
    @PutMapping
    public ResponseEntity<AccountResponse> updateAccount(
            @Validated @RequestBody AccountForm accountForm,
            @RequestParam("acctPid") UUID accountPublicId) {


        AccountResponse responseDto = accountModifier.update(accountForm, accountPublicId);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }





}
