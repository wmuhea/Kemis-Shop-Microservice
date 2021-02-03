package com.kemisshop.accountservice.security;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
    wontgn created on 12/14/20 inside the package - com.kemisshop.accountservice.security
*/

public class CustomUserDetailService<T extends Account> implements UserDetailsService {

    @Autowired
    AccountRepository<T> accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new CustomUserDetails(accountRepository.findByEmail(username));
    }
}
