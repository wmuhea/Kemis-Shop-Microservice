package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.model.ProductCategory;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager testEntityManager;


    @Test
    void should_Find_Saved_SavedProduct_By_PublicProductId() {

        List<ProductCategory> pcList = TestEntitiesFactory.buildProductCategoryList();
        Product prTest = TestEntitiesFactory.buildProductEntity();

        // saved to H2 database
        pcList.forEach(testEntityManager::persist);
        ProductCategory pcKemis = categoryRepository.findProductCategoryByCategory(Category.Kemis)
                .orElseThrow(NoSuchElementException::new);

        testEntityManager.persist(prTest);
        assertEquals(pcKemis.getCategory(), Category.findByLabel("kemis"));
        Product dbProduct = productRepository.findByPublicProductId(prTest.getPublicProductId())
                .orElseThrow(NoSuchElementException::new);
        assertEquals(prTest.getPrice(), dbProduct.getPrice());
    }

    @Test
    void should_returnFromPageObject_WithListOfProducts_Of_PageSize() {
       List<Product> productList = TestEntitiesFactory.buildProductEntityList();
       productList.forEach(
               testEntityManager::persist
       );
       int pageSize = 2;
       int pageNumber = 0;
       Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize));

       assertEquals(2, products.getContent().size());
    }

    @Test
    void should_return_ListOfProducts_withSize_definedInPageRequestOrLess_for_SpecificSellerId() {
        Product product = TestEntitiesFactory.buildProductEntity();
        testEntityManager.persist(product);
        int pageSize = 1;
        int pageNumber = 0;
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize));

        assertEquals(pageSize, products.getContent().size());
        assertEquals(product.getPublicSellerId(), products.getContent().get(0).getPublicSellerId());
    }
}
