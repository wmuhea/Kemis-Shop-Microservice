package com.kemisshop.accountservice.adapter.persistence.repository;

import com.kemisshop.accountservice.app.model.Account;
import com.kemisshop.accountservice.app.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AccountRepo <T extends Account> extends JpaRepository<T, Long> {

    T findByPublicAccountId(UUID publicId);

    T findByEmail(String email);

    T save(T account);

    T findByAccountTypeRole(Pageable pageable, Role accountRole);

    void deleteByPublicAccountId(UUID publicId);

}
