package com.kemisshop.catalogservice.app.port.in;

import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;

public interface CategoryQueryUseCase {
    ProductCategory findOne(Category category);
}
