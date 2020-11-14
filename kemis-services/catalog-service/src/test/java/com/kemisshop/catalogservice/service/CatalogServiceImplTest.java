package com.kemisshop.catalogservice.service;

import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.repository.CategoryRepository;
import com.kemisshop.catalogservice.repository.ProductRepository;
import com.kemisshop.catalogservice.repository.RatingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CatalogServiceImplTest {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DtoEntityMapper mapper;


    @Test
    void createOne() {
        Product product = TestEntitiesFactory.buildProductEntity();
        /*MockMultipartFile file = new MockMultipartFile()*/
       /* when(catalogService.createOne(product, Category.findByLabel("Kemis"), )).then()*/
    }

    @Test
    void updateOne() {
    }

    @Test
    void deleteOne() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findProductCategoryByCategory() {
    }

    @Test
    void testCreateOne() {
    }

    @Test
    void getAll() {
    }

    @Test
    void loadProductImage() {
    }

    @Test
    void rateProduct() {
    }

    @Test
    void readRatings() {
    }

    @Test
    void updateUnitsInStock() {
    }

    @Test
    void approveRating() {
    }

    @Test
    void approveProduct() {
    }
}