package com.kemisshop.accountservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdminAccount extends Account implements Serializable {

    private static final long serialVersionUID = -7606063466783330545L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;


    public AdminAccount(String email, String password, AccountType accountType) {
        super(email, password, accountType);
    }
}
