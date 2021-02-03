package com.kemisshop.accountservice.mapper;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.AdminAccount;
import com.kemisshop.accountservice.app.model.BuyerAccount;
import com.kemisshop.accountservice.app.model.SellerAccount;

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
