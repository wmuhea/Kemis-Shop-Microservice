package com.kemisshop.catalogservice.adapter.persistence;

import com.kemisshop.catalogservice.app.port.out.ModifyCategoryOutPort;
import com.kemisshop.catalogservice.app.port.out.CategoryQueryOutPort;
import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
    wontgn created on 12/27/20 inside the package - com.kemisshop.catalog.adapter.persistence
*/
@Component
public class CategoryPersistenceAdapter implements
        CategoryQueryOutPort,
        ModifyCategoryOutPort {

    private final CategoryRepository categoryRepository;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<ProductCategory> findOne(Category category) {
        return categoryRepository
                .findProductCategoryByCategory(category);

    }

   @Override
   public ProductCategory save(ProductCategory productCategory) {
       return categoryRepository
               .save(productCategory);
   }
   }
