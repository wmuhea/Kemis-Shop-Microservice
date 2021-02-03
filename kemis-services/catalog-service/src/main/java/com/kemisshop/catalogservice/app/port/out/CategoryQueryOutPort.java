package com.kemisshop.catalogservice.app.port.out;

import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;

import java.util.Optional;

public interface CategoryQueryOutPort {
    Optional<ProductCategory> findOne(Category category);
}
