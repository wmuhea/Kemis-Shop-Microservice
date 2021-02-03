package com.kemisshop.accountservice.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
 public class BuyerAccount extends Account implements Serializable {

    private static final long serialVersionUID = 1672631277538082180L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(name = "POINTS")
    private Long points = 0l;

    @ManyToMany(mappedBy = "followers")
    @JsonManagedReference
    private Set<SellerAccount> favoriteSellers = new HashSet<>();

    public BuyerAccount(String email, String password, AccountType accountType) {
        super(email, password, accountType);
    }

    public void addFavSellers(SellerAccount seller) {
        favoriteSellers.add(seller);
    }


}
