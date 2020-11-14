package com.kemisshop.accountservice.api;

import com.kemisshop.accountservice.dto.AccountRequestDto;
import com.kemisshop.accountservice.dto.AccountResponseDto;
import com.kemisshop.accountservice.dto.ResponseBean;
import com.kemisshop.accountservice.dto.UserProfileDto;
import com.famshop.authorizationservice.model.*;
import com.kemisshop.accountservice.mapper.AccountMapper;
import com.kemisshop.accountservice.mapper.UserProfileMapper;
import com.kemisshop.accountservice.model.Account;
import com.kemisshop.accountservice.model.AccountType;
import com.kemisshop.accountservice.model.UserProfile;
import com.kemisshop.accountservice.service.AccountService;
import com.kemisshop.accountservice.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/account-service")
public class UserApiController {

    private final AccountService<Account> accountService;
    private final AccountMapper accountMapper;
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserApiController(AccountService<Account> accountService, AccountMapper accountMapper, UserProfileMapper userProfileMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.userProfileMapper = userProfileMapper;
    }

    @PostMapping("/account")
    public ResponseBean createAccount(@Validated @RequestBody AccountRequestDto accountRequestDto) {

        Account account = accountMapper.toAccount(accountRequestDto);
        Type accountTypeEnum = Type.findByLabel(accountRequestDto.getType());
        AccountType accountTypeSavedInDb = accountService.getAccountType(accountTypeEnum);
        account.setAccountType(accountTypeSavedInDb);
        accountService.save(account);
        return accountMapper.toResponseBean( HttpStatus.CREATED,"Your account is created successfully");
    }

    @PutMapping("/userprofile/{acct-id}")
    public ResponseBean createUserProfile(@Validated @RequestBody UserProfileDto userProfileDto,
                                                    @PathVariable(name = "acct-id") UUID publicAccountId) {

        // changed it via mapstruct update later
        UserProfile userProfile = userProfileMapper.toProfile(userProfileDto);
        AccountResponseDto responseDto = accountService.updateProfileInformation(publicAccountId, userProfile );
        return accountMapper.toResponseBean( HttpStatus.CREATED, responseDto);
    }

    @GetMapping("/accounts/{accountType}")
    public ResponseBean getAccounts(@RequestParam("p") Integer page,
                                                @RequestParam("s") Integer size,
                                                @PathVariable("accountType") String acctType) {

        Page<AccountResponseDto> accountResponseDtos = accountService.getAccounts(page, size, Type.findByLabel(acctType));
        return accountMapper.toResponseBean(HttpStatus.OK, accountResponseDtos);
    }

    @GetMapping("/account/{id}")
    public ResponseBean getAccount(@PathVariable("id") UUID publicId) {

        AccountResponseDto responseDto = accountService.findById(publicId);
        return accountMapper.toResponseBean(HttpStatus.FOUND, responseDto);
    }

    @PutMapping("/account/activate/{id}")
    public ResponseBean activateAccount( @PathVariable("id") UUID publicId) {

       accountService.activateAccount(publicId);
       return accountMapper.toResponseBean(HttpStatus.OK, "Your account is activated successfully");
    }

    @DeleteMapping("/account/deactivate/{id}")
    public ResponseBean deleteAccount( @PathVariable("id") UUID publicId) {
        accountService.deleteById(publicId);
        return accountMapper.toResponseBean( HttpStatus.ACCEPTED,"Your account deletion request is accepted successfully");
    }

    @PatchMapping("/buyer/{uuid}")
    public void addPointsToBuyer(@PathVariable("uuid") UUID publicId) {
        accountService.updateDiscountPoints(publicId);
    }

    @PatchMapping("/accounts/follow/{buyerId}/{sellerId}")
    public ResponseBean addFavoriteSeller(@PathVariable UUID buyerId, @PathVariable UUID sellerId) {

        accountService.followSeller(buyerId, sellerId);
        return accountMapper.toResponseBean(HttpStatus.OK, "Favorite Seller is added successfully");

    }

}
