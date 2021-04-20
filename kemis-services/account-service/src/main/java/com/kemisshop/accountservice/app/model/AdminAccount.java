package com.kemisshop.accountservice.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdminAccount extends Account implements Serializable {

    private static final long serialVersionUID = -7606063466783330545L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;


    private AdminAccount(String fullName, String email,
                          String password, String phoneNumber,
                          AccountType accountType) {

        super(fullName, email, password, phoneNumber, accountType);

    }
}
