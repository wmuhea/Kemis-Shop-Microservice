package com.kemisshop.accountservice.app.model;


import com.kemisshop.accountservice.app.dto.request.AddressForm;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EnableJpaAuditing  //For the created at and updated at annotations
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(unique = true)
    private UUID publicAccountId;

    @Column(name = "Full_Name")
    private String fullName;

    @Column(name = "Email")
    @Email
    private String email;

    @Column(name = "Password")
    @NotEmpty
    private String password;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountType_Id")
    private AccountType accountType;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Address> addresses;

    @CreationTimestamp
    private Date createDayTime;

    @UpdateTimestamp
    private Date updateDayTime;

    public Account() {
    }

    public Account(String fullName, String email,
                   String password, String phoneNumber, AccountType accountType) {

        this.publicAccountId = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addresses = new HashSet<>();
        this.accountType = accountType;
    }

    public void addAddress(AddressForm addressForm) {
        Address newAddress = Address.of(addressForm);
        addresses.add(newAddress);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return Id;
    }

    public UUID getPublicAccountId() {
        return publicAccountId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Date getCreateDayTime() {
        return createDayTime;
    }

    public Date getUpdateDayTime() {
        return updateDayTime;
    }
}
