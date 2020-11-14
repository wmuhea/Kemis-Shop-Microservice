package com.kemisshop.accountservice.repository;

import com.kemisshop.accountservice.model.Account;
import com.kemisshop.accountservice.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository <T extends Account> extends JpaRepository<T, Long> {

    <T extends Account> T findByPublicAccountId(UUID publicId);

    <T extends Account> T save(T account);

    <T extends Account> Page<T> findByAccountTypeType(Pageable pageable, Type accountType);


    void deleteByPublicAccountId(UUID publicId);

}
