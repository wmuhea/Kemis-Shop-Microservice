package com.kemisshop.accountservice.service;


import com.kemisshop.accountservice.dto.AccountResponseDto;
import com.kemisshop.accountservice.model.Account;
import com.kemisshop.accountservice.model.AccountType;
import com.kemisshop.accountservice.model.Type;
import com.kemisshop.accountservice.model.UserProfile;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AccountService<T extends Account> {

     AccountResponseDto save(T account) ;

     AccountResponseDto updateProfileInformation(UUID acctPublicId, UserProfile userProfile);

     AccountResponseDto findById(UUID acctPublicId);

     void deleteById(UUID acctPublicId);

     Page<AccountResponseDto> getAccounts(Integer pageNumber, Integer size, Type accountType);

     void followSeller(UUID acctPublicId1, UUID acctPublicId2);

      void activateAccount(UUID sellerPublicId);

     void updateDiscountPoints(UUID acctPublicId);

     AccountType getAccountType(Type type);

     AccountType save(AccountType accountType);


}
