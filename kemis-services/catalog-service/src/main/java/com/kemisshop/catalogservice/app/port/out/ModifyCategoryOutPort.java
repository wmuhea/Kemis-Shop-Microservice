package com.kemisshop.catalogservice.app.port.out;

import com.kemisshop.catalogservice.domain.ProductCategory;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app.port.out
*/
public interface ModifyCategoryOutPort {
    ProductCategory save(ProductCategory category);
}
