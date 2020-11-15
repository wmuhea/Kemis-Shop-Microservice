package com.kemisshop.catalogservice.service;


import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.dto.RatingDto;
import com.kemisshop.catalogservice.dto.ResponsePayLoad;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.model.ProductCategory;
import com.kemisshop.catalogservice.model.Rating;
import com.kemisshop.catalogservice.exceptions.FileStorageException;
import com.kemisshop.catalogservice.repository.ProductRepository;
import com.kemisshop.catalogservice.repository.CategoryRepository;
import com.kemisshop.catalogservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

    private static final String  UPLOAD_DIR = "kemis-services/product-images";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ResourceLoader resourceLoader;
    private final RatingRepository ratingRepository;
    private final DtoEntityMapper mapper;

    @Autowired
    public CatalogServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ResourceLoader resourceLoader, RatingRepository ratingRepository,
                              DtoEntityMapper mapper) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.resourceLoader = resourceLoader;
        this.ratingRepository = ratingRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto createOne(Product product, Category category, MultipartFile file)  throws NoSuchElementException{

        ProductCategory productCategory =
        categoryRepository.findProductCategoryByCategory(category)
                .orElseThrow(NoSuchElementException::new);

        product.setPublicProductId(UUID.randomUUID());
        product.setImageName(this.storeProductImage(file, product));
        productCategory.addProduct(product);
        categoryRepository.save(productCategory); // saves the product as well
        return mapper.toDtoWithLink(productRepository.save(product));
    }

    @Override
    public ProductDto updateOne(Product product, UUID publicId) {

        Product productReadFromDb = productRepository
                .findByPublicProductId(publicId)
                .orElseThrow(NoSuchElementException::new);

        productReadFromDb.setName(product.getName());
        productReadFromDb.setDescription(product.getDescription());
        productReadFromDb.setPrice(product.getPrice());
        product.setUnitsInStock(product.getUnitsInStock());

        return mapper.toDtoWithLink(product);
    }


    @Override
    public String deleteOne(UUID publicId, String imageName) {
        Optional<Product> product = productRepository.deleteByPublicProductId(publicId);
        deleteProductImage(imageName);
        return "Your product is deleted";
    }


    @Override
    public Map<String, Object> findOne(UUID publicId) {
        Product product = productRepository.findByPublicProductId(publicId)
                .orElseThrow(NoSuchElementException::new);

       // Get the first 5 approved ratings with the product
        List<RatingDto> ratings = ratingRepository
                .findByProduct_PublicProductIdAndApproved(publicId, true, PageRequest.of(0, 5))
                .getContent()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

       Map<String, Object> productWithRatings = new HashMap<>();
       productWithRatings.put("product", mapper.toDtoWithLink(product));
       productWithRatings.put("ratings", ratings);

       return productWithRatings;

    }

    @Override
    public ProductCategory findProductCategoryByCategory(Category category) {

        return categoryRepository.findProductCategoryByCategory(category)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public ProductCategory createOne(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {

        return productRepository.findAll(pageable)
                .map(mapper::toDtoWithLink);

    }


    @Override
    public void rateProduct(Rating rating, UUID productPublicId) {
        // fetch product and set it
        Product importedProduct = productRepository.findByPublicProductId(productPublicId)
                .orElseThrow(NoSuchElementException::new);

        rating.setProduct(importedProduct);

        rating.setRatingPublicId(UUID.randomUUID());
    }

    @Override
    public ResponsePayLoad readRatings(UUID publicProductId, Pageable pageable) {
        List<RatingDto> ratingDtos = ratingRepository
                .findByProduct_PublicProductId(publicProductId, pageable)
                .getContent().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return ResponsePayLoad.builder()
                .ratings(ratingDtos).build();
    }

    @Override
    public void updateUnitsInStock(UUID productPublicId) {
        Product purchasedProduct = productRepository.findByPublicProductId(productPublicId)
                .orElseThrow(NoSuchElementException::new);
        Integer updateUnitsInStock = purchasedProduct.getUnitsInStock() - 1;
        purchasedProduct.setUnitsInStock(updateUnitsInStock);
        productRepository.save(purchasedProduct);

    }

    @Override
    public void approveRating(UUID publicProductId, UUID publicRatingId) {
        Rating rating = ratingRepository
                .findByProduct_PublicProductIdAndRatingPublicId(publicProductId, publicRatingId)
                .orElseThrow(NoSuchElementException::new);
        rating.approve();
        ratingRepository.save(rating);
    }

    @Override
    public void approveProduct(UUID publicProductId) {
        Product product = productRepository.findByPublicProductId(publicProductId)
                .orElseThrow(NoSuchElementException::new);
        product.approve();
        productRepository.save(product);
    }

    // For Image access while loading products
    public Resource loadProductImage(String fileName) {
        return resourceLoader.getResource("file:"+ UPLOAD_DIR + "/" + fileName);
    }

    private String storeProductImage(MultipartFile file, Product product) {
        // The image file name will be unique as it is set to product name + public Seller Id
        String fileName = product.getName() +"_" + product.getPublicSellerId().toString()+  ".jpg";

        if(!file.isEmpty()) {

            try(InputStream inputStream = file.getInputStream()) {
                // Check file name
                Files.copy(inputStream, Paths.get(UPLOAD_DIR, fileName), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
            }

        }

        return fileName;
    }


    private void deleteProductImage(String productImageName) {
        try {
            Files.delete(Paths.get(UPLOAD_DIR, productImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

