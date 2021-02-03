package com.kemisshop.accountservice.service;


import com.kemisshop.accountservice.dto.AccountResponseDto;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import com.kemisshop.accountservice.app.model.UserProfile;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AccountService<T extends Account> {

     AccountResponseDto save(T account) ;

     AccountResponseDto updateProfileInformation(UUID acctPublicId, UserProfile userProfile);

     AccountResponseDto findById(UUID acctPublicId);

     void deleteById(UUID acctPublicId);

     Page<AccountResponseDto> getAccounts(Integer pageNumber, Integer size, Role accountRole);

     void followSeller(UUID acctPublicId1, UUID acctPublicId2);

      void activateAccount(UUID sellerPublicId);

     void updateDiscountPoints(UUID acctPublicId);

     AccountType getAccountType(Role role);

     AccountType save(AccountType accountType);


}
