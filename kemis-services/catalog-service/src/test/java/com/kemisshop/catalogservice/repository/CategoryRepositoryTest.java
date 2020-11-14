package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.ProductCategory;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void should_Find_ProductCategory_By_CategoryEnum() {
       ProductCategory pc = TestEntitiesFactory.buildProductCategory();

        // saved to H2 database
        testEntityManager.persist(pc);
        ProductCategory pcKemis = categoryRepository
                .findProductCategoryByCategory(Category.Kemis)
                .orElseThrow(NoSuchElementException::new);

        assertEquals(pcKemis.getCategory(), Category.findByLabel("kemis"));
    }

}