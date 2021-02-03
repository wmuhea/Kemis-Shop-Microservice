package com.kemisshop.accountservice.service;

import com.kemisshop.accountservice.dto.AccountResponseDto;
import com.kemisshop.accountservice.mapper.AccountMapper;
import com.kemisshop.accountservice.app.model.*;
import com.kemisshop.accountservice.repository.AccountRepository;
import com.kemisshop.accountservice.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl<T extends Account> implements AccountService<T> {

    @Autowired
    private AccountRepository<T> accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public AccountResponseDto save(T account) {
        account.setPublicAccountId(UUID.randomUUID());
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return  accountMapper.toDto(account);
    }

    @Override
    public AccountResponseDto updateProfileInformation(UUID acctPublicId, UserProfile userProfile) {

        T importedAcct = accountRepository.findByPublicAccountId(acctPublicId);
        importedAcct.setUserProfile(userProfile);
        T account = accountRepository.save(importedAcct);
        return accountMapper.toDto(account);
    }

    @Override
    public AccountResponseDto findById(UUID acctPublicId) {
        Account account = accountRepository.findByPublicAccountId(acctPublicId);
        return accountMapper.toDto(account);
    }

    @Override
    public void deleteById(UUID acctPublicId) {
       accountRepository.deleteByPublicAccountId(acctPublicId);
    }

    @Override
    public Page<AccountResponseDto> getAccounts(Integer pageNumber, Integer size, Role accountRole) {
        Page<T> accounts = accountRepository.findByAccountTypeRole(PageRequest.of(pageNumber, size), accountRole);
        return accounts
                .map(account -> accountMapper.toDto(account));

    }

    @Override
    public void followSeller(UUID buyerPublicId, UUID sellerPublicId) {

       SellerAccount seller = accountRepository.findByPublicAccountId(sellerPublicId);
       BuyerAccount buyerAccount = accountRepository.findByPublicAccountId(buyerPublicId);
       buyerAccount.addFavSellers(seller);
       seller.addFollowers(buyerAccount);
       accountRepository.save(buyerAccount);
       accountRepository.save(seller);

    }

    @Override
    public void activateAccount(UUID sellerPublicId) {

        SellerAccount seller = accountRepository.findByPublicAccountId(sellerPublicId);
        seller.setApproved();
        accountRepository.save(seller);
    }


    @Override
    public void updateDiscountPoints(UUID buyerPublicId) {
        BuyerAccount buyerAccount = accountRepository.findByPublicAccountId(buyerPublicId);
        buyerAccount.setPoints(buyerAccount.getPoints() + 1);
        accountRepository.save(buyerAccount);
    }

    @Override
    public AccountType getAccountType(Role role) {
        return accountTypeRepository.findAccountTypeByRole(role);
    }

    @Override
    public AccountType save(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

}
