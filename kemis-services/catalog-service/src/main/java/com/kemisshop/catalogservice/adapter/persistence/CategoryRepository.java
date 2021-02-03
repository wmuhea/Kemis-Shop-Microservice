package com.kemisshop.catalogservice.adapter.persistence;

import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findProductCategoryByCategory(Category category);
}
