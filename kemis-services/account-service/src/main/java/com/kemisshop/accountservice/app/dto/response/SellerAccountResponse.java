package com.kemisshop.accountservice.app.dto.response;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.SellerAccount;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SellerAccountResponse extends AccountResponse {
    private Boolean approved;
    private Long numberOfFollowers;

    private SellerAccountResponse(Account account) {
        super(account);
    }

    public static AccountResponse of(Account account) {
        SellerAccount sellerAccount = (SellerAccount) account;
        SellerAccountResponse response =
                new SellerAccountResponse(sellerAccount);

        response.setApproved(sellerAccount.isApproved());
        response.setNumberOfFollowers(sellerAccount.getNumberOfFollowers());
        return response;
    }
}
