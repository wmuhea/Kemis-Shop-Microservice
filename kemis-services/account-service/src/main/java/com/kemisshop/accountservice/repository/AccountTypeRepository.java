package com.kemisshop.accountservice.repository;

import com.kemisshop.accountservice.model.AccountType;
import com.kemisshop.accountservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    AccountType findAccountTypeByType(Type type);

}
