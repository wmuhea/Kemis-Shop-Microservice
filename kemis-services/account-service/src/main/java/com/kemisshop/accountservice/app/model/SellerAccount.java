package com.kemisshop.accountservice.app.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

@Entity
@Table(name = "SellerAccount")
public class SellerAccount extends Account implements Serializable {
    private static final long serialVersionUID = 551664287612013332L;

    private Boolean approved;

    private Long numberOfFollowers;

    public Boolean isApproved() {
        return approved;
    }

    public void approve() {
        this.approved = true;
    }

    public Long getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void incrementFollowerNumber() {
        this.numberOfFollowers++;
    }

    public SellerAccount () {

    }
    private SellerAccount(String fullName, String email,
                         String password, String phoneNumber,
                         AccountType accountType) {

        super(fullName, email, password, phoneNumber, accountType);
        this.approved = false;
        this.numberOfFollowers = 0l;
    }

    public static SellerAccount of(String fullName, String email,
                                   String password, String phoneNumber,
                                   AccountType accountType) {

        return new SellerAccount(fullName, email, password, phoneNumber, accountType);
    }
}
