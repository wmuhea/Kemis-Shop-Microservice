package com.kemisshop.accountservice.app.model;

import com.kemisshop.accountservice.app.dto.request.AddressForm;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

@Entity
@EnableJpaAuditing
@Table(name = "Address")
public class Address {

    @NotBlank
    @Column(name = "Address_Line_One")
    private String street;

    @NotBlank
    @Column(name = "Address_Line_Two")
    private String apartmentNumber;

    @NotBlank
    @Column(name = "City")
    private String city;

    @NotBlank
    @Column(name = "State")
    private String state;

    @NotNull
    @Column(name="Zip_Code")
    private Integer zipCode;

    @NotBlank
    @Column(name = "Country")
    private String country;

    @CreationTimestamp
    private Date createDayTime;

    @UpdateTimestamp
    private Date updateDayTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


    public Address() {
    }

    public Address(String street, String apartmentNumber, String city,
                   String state, String country, Integer zipCode) {

        this.street = street;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static Address of(AddressForm addressForm) {
        Address newAddress = new Address();
        newAddress.setStreet(addressForm.getStreet());
        newAddress.setApartmentNumber(addressForm.getApartment());
        newAddress.setCity(addressForm.getCity());
        newAddress.setState(addressForm.getState());
        newAddress.setZipCode(addressForm.getZipCode());
        newAddress.setCountry(addressForm.getCountry());

        return newAddress;
    }
}
