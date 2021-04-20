package com.kemisshop.accountservice.app.dto.response;



import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.BuyerAccount;
import lombok.*;


@Getter
@Setter
public class BuyerAccountResponse extends AccountResponse {

    private Long discountPoints;

    private Integer numberOfFavoriteSellers;

    public BuyerAccountResponse(){
    }


    private BuyerAccountResponse(Account account) {
        super(account);
    }

    public static AccountResponse of(Account account) {
         BuyerAccountResponse response =
                 new BuyerAccountResponse(account);

         response.setDiscountPoints(
                 ((BuyerAccount)account).getPoints()
         );
         response.setNumberOfFavoriteSellers(
                 ((BuyerAccount)account).countFavoriteSellers()
         );

         return response;
    }
}
