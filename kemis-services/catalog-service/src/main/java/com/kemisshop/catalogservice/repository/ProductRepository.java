package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> deleteByPublicProductId(UUID publicId);
    Optional<Product> findByPublicProductId(UUID publicId);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByPublicSellerId(UUID sellerId, Pageable pageable);
}
