package com.kemisshop.catalogservice.app;

import com.kemisshop.catalogservice.adapter.persistence.CategoryPersistenceAdapter;
import com.kemisshop.catalogservice.app.port.in.ModifyCategoryInPort;
import com.kemisshop.catalogservice.domain.ProductCategory;
import org.springframework.stereotype.Service;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app.port
*/
@Service
public class ModifyCategoryServiceImpl implements ModifyCategoryInPort {

    private final CategoryPersistenceAdapter categoryPersistenceAdapter;

    public ModifyCategoryServiceImpl(CategoryPersistenceAdapter categoryPersistenceAdapter) {
        this.categoryPersistenceAdapter = categoryPersistenceAdapter;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryPersistenceAdapter
                .save(productCategory);
    }
}
