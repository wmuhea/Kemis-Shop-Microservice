package com.kemisshop.accountservice.adapter.persistence;

import com.kemisshop.accountservice.adapter.persistence.repository.AccountRepo;
import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AdminAccount;
import com.kemisshop.accountservice.app.model.BuyerAccount;
import com.kemisshop.accountservice.app.model.SellerAccount;
import com.kemisshop.accountservice.app.port.out.CreateAccountPort;
import com.kemisshop.accountservice.app.port.out.LoadAccountPort;
import com.kemisshop.accountservice.app.port.out.UpdateAccountPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
    wontgn created on 12/31/20 inside the package - com.kemisshop.accountservice.adapter.persistence
*/
@Component
public class BuyerAccountPersistenceOutAdapter<T extends Account> implements
        CreateAccountPort, UpdateAccountPort, LoadAccountPort {

    private final AccountRepo<T> accountRepo;

    public BuyerAccountPersistenceOutAdapter(
           AccountRepo<T> accountRepo) {
        this.accountRepo = accountRepo;

        this.buyerAccountRepo = buyerAccountRepo;
        this.sellerAccountRepo = sellerAccountRepo;
        this.adminAccountRepo = adminAccountRepo;
    }

    public Account  save(BuyerAccount buyerAccount) {
        return buyerAccountRepo.save(buyerAccount);
    }

    public Account findOne(UUID buyerPublicId) {
        return buyerAccountRepo.findByPublicAccountId(buyerPublicId);
    }

    public void deleteOne(UUID buyerPublicId) {
        buyerAccountRepo.deleteByPublicAccountId(buyerPublicId);
    }


    @Override
    public Account save(Account account) {
        return null;
    }
}
