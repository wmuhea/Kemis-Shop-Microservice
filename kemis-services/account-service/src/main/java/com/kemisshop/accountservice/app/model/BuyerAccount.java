package com.kemisshop.accountservice.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

@Entity
 public class BuyerAccount extends Account implements Serializable {

    private static final long serialVersionUID = 1672631277538082180L;

    @Column(name = "Discount_Points")
    private Long points = 0l;

    @ManyToMany
    @JoinTable(name = "Favorite_Sellers",
               joinColumns = {@JoinColumn(name = "Buyer_Id")},
               inverseJoinColumns = {@JoinColumn(name = "Seller_Id")})
    private Set<SellerAccount> favoriteSellers;

   public BuyerAccount() {
   }

   private BuyerAccount(String fullName, String email,
                       String password, String phoneNumber, AccountType accountType) {

         super(fullName,email,password,phoneNumber, accountType);
         this.favoriteSellers = new HashSet<>();
   }

    public Long getPoints() {
        return points;
    }

    public void incrementDiscountPoints() {
        this.points++;
    }

    public Integer countFavoriteSellers() {
        return favoriteSellers.size();
    }

   public void addFavSellers(SellerAccount seller) {
        favoriteSellers.add(seller);
   }

   public static BuyerAccount of(String fullName, String email,
                                 String password, String phoneNumber,
                                 AccountType accountType) {

        return new BuyerAccount(fullName,email,password,phoneNumber, accountType);

   }


}
