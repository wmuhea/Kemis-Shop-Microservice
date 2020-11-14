package com.kemisshop.accountservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@EnableJpaAuditing  //For the created at and updated at annotations
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @JsonIgnore
    @Column(unique = true)
    private UUID publicAccountId;

    @Column(name = "Email")
    @Email
    private String email;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "Not null or Not empty")
    private String password;

    @JsonIgnore
    @OneToOne
    private AccountType accountType;

    @JsonIgnore
    @CreationTimestamp
    private Date createDayTime;

    @JsonIgnore
    @UpdateTimestamp
    private Date updateDayTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    public Account(String email, String password, AccountType accountType) {
        this.email = email;
        this.publicAccountId = UUID.randomUUID();
        this.password = password;
        this.accountType = accountType;
    }
}
