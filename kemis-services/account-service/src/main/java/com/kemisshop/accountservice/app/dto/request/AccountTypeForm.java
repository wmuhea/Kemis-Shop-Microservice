package com.kemisshop.accountservice.app.dto.request;

import java.io.Serializable;

/*
    wontgn created on 2/12/21 inside the package - com.kemisshop.accountservice.app.dto.request
*/
public class AccountTypeForm implements Serializable {

    private static final long serialVersionUID = -8930822229727263696L;

    private String role;

    public AccountTypeForm() {
    }

    public AccountTypeForm(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
