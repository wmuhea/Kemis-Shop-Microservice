package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @HystrixCommand
    Optional<Product> deleteByPublicProductId(UUID publicId);

    @HystrixCommand
    Optional<Product> findByPublicProductId(UUID publicId);

    @HystrixCommand
    Page<Product> findAll(Pageable pageable);

    @HystrixCommand
    Page<Product> findByPublicSellerId(UUID sellerId, Pageable pageable);
}
