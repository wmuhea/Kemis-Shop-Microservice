package com.kemisshop.accountservice.adapter.persistence.repository;

import com.kemisshop.accountservice.app.model.AdminAccount;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminRepository extends AccountRepo<AdminAccount> {
}
