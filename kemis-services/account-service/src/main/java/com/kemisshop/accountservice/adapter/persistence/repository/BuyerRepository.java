package com.kemisshop.accountservice.adapter.persistence.repository;

import com.kemisshop.accountservice.app.model.BuyerAccount;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BuyerRepository extends AccountRepo<BuyerAccount> {
}
