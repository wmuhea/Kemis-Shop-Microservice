package com.kemisshop.catalogservice.adapter.persistence;

import com.kemisshop.catalogservice.domain.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> deleteByPublicProductId(UUID publicId);

    @Query(value = "Select p From Product p where p.publicProductId = ?1")
    Optional<Product> findByPublicProductId(UUID publicId);

    Page<Product> findAll(Pageable pageable);

    @Query(value = "select p from Product  p where p.publicSellerId = ?1")
    Page<Product> findByPublicSellerId(UUID sellerId, Pageable pageable);
}
