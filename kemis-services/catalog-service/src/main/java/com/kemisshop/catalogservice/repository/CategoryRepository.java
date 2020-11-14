package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findProductCategoryByCategory(Category category);
}
