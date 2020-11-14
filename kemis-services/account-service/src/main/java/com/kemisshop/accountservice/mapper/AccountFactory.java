package com.kemisshop.accountservice.mapper;

import com.kemisshop.accountservice.model.Account;
import com.kemisshop.accountservice.model.AdminAccount;
import com.kemisshop.accountservice.model.BuyerAccount;
import com.kemisshop.accountservice.model.SellerAccount;

public class AccountFactory {

    public static Account getAccount(String name) {
        Account account = null;
        switch (name) {
            case "Buyer":
                account = new BuyerAccount();
                break;
            case "Seller":
                account = new SellerAccount();
                break;
            case "Admin":
                account = new AdminAccount();
                break;
        }
        return account;
    }


}
