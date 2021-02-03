package com.kemisshop.accountservice.adapter.persistence.repository;

import com.kemisshop.accountservice.app.model.SellerAccount;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SellerRepository extends AccountRepo<SellerAccount> {
}
