package com.kemisshop.accountservice.adapter.persistence.repository;

import com.kemisshop.accountservice.app.model.AccountType;
import com.kemisshop.accountservice.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepo extends JpaRepository<AccountType, Long> {

    AccountType findAccountTypeByRole(Role role);


}
