package com.kemisshop.catalogservice.adapter.persistence;

import com.kemisshop.catalogservice.app.port.out.ModifyProductOutPort;
import com.kemisshop.catalogservice.app.port.out.ProductQueryOutPort;
import com.kemisshop.catalogservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/*
    wontgn created on 12/27/20 inside the package - com.kemisshop.catalog.adapter.persistence
*/
@Component
public class ProductPersistenceAdapter implements ModifyProductOutPort, ProductQueryOutPort {
    private final ProductRepository productRepository;

    public ProductPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save (Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> deleteOne(UUID productPublicId) {
        return productRepository
                .deleteByPublicProductId(productPublicId);

    }

    public Optional<Product> findOne(UUID publicProductId) {
        return productRepository
                .findByPublicProductId(publicProductId);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository
                .findAll(pageable);
    }

    public void approveProduct(UUID publicProductId) {
        Product product = productRepository.findByPublicProductId(publicProductId)
                .orElseThrow(NoSuchElementException::new);
        product.approve();
        productRepository.save(product);
    }


}
