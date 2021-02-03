package com.kemisshop.accountservice.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
public class SellerAccount extends Account implements Serializable {
    private static final long serialVersionUID = 551664287612013332L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private Boolean approved = false;

    @ManyToMany
    @JoinTable(name = "FAV_SELLERS", joinColumns = @JoinColumn(name = "SELLER_ID"),
               inverseJoinColumns = @JoinColumn(name = "BUYER_ID"))
    @JsonBackReference
    private Set<BuyerAccount> followers = new HashSet<>();


    public void addFollowers(BuyerAccount buyerAccount) {
        followers.add(buyerAccount);
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved() {
        this.approved = true;
    }

    public Set<BuyerAccount> getFollowers() {
        return followers;
    }

}
