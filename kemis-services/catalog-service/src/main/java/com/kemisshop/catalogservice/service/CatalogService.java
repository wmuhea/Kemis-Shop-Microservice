package com.kemisshop.catalogservice.service;

import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.dto.ResponsePayLoad;
import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.model.ProductCategory;
import com.kemisshop.catalogservice.model.Rating;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface CatalogService {

    ProductDto createOne(Product product, Category category, MultipartFile file);
    ProductDto updateOne(Product product, UUID publicId);
    String deleteOne(UUID publicId) throws IOException;
    Map<String, Object> findOne(UUID publicId);
    ProductCategory findProductCategoryByCategory(Category category);
    ProductCategory createOne(ProductCategory productCategory);
    Page<ProductDto> getAll(Pageable pageable);
    Resource loadProductImage(String fileName);
    void rateProduct(Rating rating, UUID productPublicId);
    ResponsePayLoad readRatings(UUID publicProductId, Pageable pageable);
    void updateUnitsInStock(UUID productPublicId);
    void approveRating(UUID publicProductId, UUID ratingProductId);
    void approveProduct(UUID publicProductId);


}
