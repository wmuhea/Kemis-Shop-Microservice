package com.kemisshop.accountservice.adapter.persistence;

import com.kemisshop.accountservice.adapter.persistence.repository.AccountRepo;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.BuyerAccount;
import com.kemisshop.accountservice.app.model.SellerAccount;

import java.util.UUID;

/*
    wontgn created on 1/5/21 inside the package - com.kemisshop.accountservice.adapter.persistence
*/
public class SellerAccountPersistenceOutAdapter {

    private final AccountRepo<SellerAccount> sellerAccountRepo;

    public SellerAccountPersistenceOutAdapter(AccountRepo<SellerAccount> sellerAccountRepo) {
        this.sellerAccountRepo = sellerAccountRepo;
    }

    public Account save(BuyerAccount buyerAccount) {
        return sellerAccountRepo.save(buyerAccount);
    }

    public Account findOne(UUID buyerPublicId) {
        return sellerAccountRepo.findByPublicAccountId(buyerPublicId);
    }

    public void deleteOne(UUID buyerPublicId) {
        sellerAccountRepo.deleteByPublicAccountId(buyerPublicId);
    }
}
