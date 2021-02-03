package com.kemisshop.accountservice.app.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "It cannot be null or empty")
    @Column(name = "FullName")
    private String fullName;


    public UserProfile() {
    }

    public UserProfile(String fullName) {
        this.fullName = fullName;
    }


}
