package com.kemisshop.accountservice.adapter.persistence.repository;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepo <T extends Account> extends JpaRepository<T, Long> {

   <T extends Account> T findByPublicAccountId(UUID publicId);

   <T extends Account> T findByEmail(String email);

    <T extends Account> T save(T account);

    <T extends Account> Page<T> findByAccountTypeRole(Pageable pageable, Role accountRole);

    void deleteByPublicAccountId(UUID publicId);

    @Query(value = "SELECT s FROM SellerAccount s WHERE s.approved = false")
    <T extends Account> Page<T> fetchUnapprovedSellerAccounts();

}
