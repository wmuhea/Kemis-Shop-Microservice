package com.kemisshop.catalogservice.app.port.in;

import com.kemisshop.catalogservice.domain.ProductCategory;

public interface ModifyCategoryInPort {
    ProductCategory save(ProductCategory productCategory);
}
