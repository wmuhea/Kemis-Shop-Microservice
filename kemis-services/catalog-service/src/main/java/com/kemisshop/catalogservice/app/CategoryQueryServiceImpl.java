package com.kemisshop.catalogservice.app;

import com.kemisshop.catalogservice.app.port.in.CategoryQueryUseCase;
import com.kemisshop.catalogservice.app.port.out.CategoryQueryOutPort;
import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app
*/

@Service
public class CategoryQueryServiceImpl implements CategoryQueryUseCase {

    private final CategoryQueryOutPort productCategoryPort;

    public CategoryQueryServiceImpl(CategoryQueryOutPort productCategoryPort) {
        this.productCategoryPort = productCategoryPort;
    }

    public ProductCategory findOne(Category category) {
        return productCategoryPort
                .findOne(category)
                .orElseThrow(NoSuchElementException::new);
    }

}
