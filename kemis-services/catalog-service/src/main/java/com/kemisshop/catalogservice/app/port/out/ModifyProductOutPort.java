package com.kemisshop.catalogservice.app.port.out;

import com.kemisshop.catalogservice.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ModifyProductOutPort {

    Product save (Product product) ;
    Optional<Product> deleteOne(UUID productPublicId);
    public void approveProduct(UUID publicProductId);
}
