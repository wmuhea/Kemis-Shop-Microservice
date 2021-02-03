package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;
import com.kemisshop.catalogservice.adapter.persistence.CategoryRepository;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //use this for testing using real db
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void when_FindProductCategoryByCategoryTriggered_ShouldFoundProductCategory() {
       ProductCategory pc = TestEntitiesFactory.buildProductCategory();

        // saved to H2 database
        testEntityManager.persist(pc);
        ProductCategory pcKemis = categoryRepository
                .findProductCategoryByCategory(Category.MEKENET)
                .orElseThrow(NoSuchElementException::new);


        assertEquals(pcKemis.getCategory(), Category.findByLabel("mekenet"));
    }

}