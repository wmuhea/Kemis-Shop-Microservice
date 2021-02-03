package com.kemisshop.accountservice.repository;

import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    AccountType findAccountTypeByRole(Role role);

}
