package com.kemisshop.catalogservice.app.port.out;

import com.kemisshop.catalogservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductQueryOutPort {

    Optional<Product> findOne(UUID publicProductId);
    Page<Product> findAll(Pageable pageable);
}
