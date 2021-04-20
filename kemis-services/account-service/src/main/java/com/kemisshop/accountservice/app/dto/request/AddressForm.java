package com.kemisshop.accountservice.app.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


/*
    wontgn created on 2/10/21 inside the package - com.kemisshop.accountservice.app.dto.request
*/
public class AddressForm {

    @JsonProperty("Line One")
    @NotBlank
    private String street;

    @JsonProperty("Line Two")
    @JsonIgnore
    private String apartment;

    @JsonProperty("City")
    @NotBlank
    private String city;

    @JsonProperty("State/Province")
    @NotBlank
    private String state;

    @JsonProperty("ZipCode")
    @NotBlank
    private Integer zipCode;

    @JsonProperty("Country")
    @NotBlank
    private String Country;


    public AddressForm() {
    }

    public AddressForm(@NotBlank String street, String apartment,
                       String city, String state, Integer zipCode,
                       String country) {

        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        Country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
