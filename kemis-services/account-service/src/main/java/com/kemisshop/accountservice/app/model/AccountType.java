package com.kemisshop.accountservice.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountType implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountType(Role role) {
        this.role = role;
    }
}
