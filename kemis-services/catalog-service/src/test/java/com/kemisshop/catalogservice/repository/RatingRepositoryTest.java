package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.model.ProductCategory;
import com.kemisshop.catalogservice.model.Rating;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RatingRepositoryTest {
    private  List<Rating> productRatings;
    private List<ProductCategory> productCategories;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
     void init() {
        productRatings = TestEntitiesFactory.buildRatingEntityList();
        productRatings.get(0).getProduct().getCategorySet().forEach(
                testEntityManager::persist
        );
        Product product = productRatings.get(0).getProduct();
        testEntityManager.persist(product);

        productCategories = TestEntitiesFactory.buildProductCategoryList();
        productRatings.forEach(rating ->
            testEntityManager.persist(rating)
        );
    }

    @Test
    void should_Return_ApprovedRatings_Of_AProduct_By_PublicProductId() {
        UUID ratedProductPublicId = productRatings.get(0)
                .getProduct()
                .getPublicProductId();

        Page<Rating> approvedRatings = ratingRepository
                .findByProduct_PublicProductIdAndApproved(
                        ratedProductPublicId, true,
                        PageRequest.of(0, 2)
                );
        // Just checking the size of the returned ratings are 2
        assertEquals("It is empty", 0, approvedRatings.getContent().size());
        assertEquals("It is empty", 0, approvedRatings.getTotalPages());

    }

    @Test
    void should_Return_Ratings_With_A_Given_PublicProductId() {
        UUID ratedProductPublicId = productRatings.get(0)
                .getProduct()
                .getPublicProductId();
        Page<Rating> allSavedRatingsOfaProduct = ratingRepository
                .findByProduct_PublicProductId(
                        ratedProductPublicId,
                        PageRequest.of(0, 3)
                );
        // Just checking the size of the returned ratings are 3
        assertEquals("They Are equal", 3, allSavedRatingsOfaProduct.getContent().size());
        // check the total pages for the rating is 2
        assertEquals("The total number of ratings is",  1, allSavedRatingsOfaProduct.getTotalPages());
    }

    @Test
    void should_Return_NotApproved_RatingsOfaProduct() {
        UUID ratedProductPublicId = productRatings.get(0)
                .getProduct()
                .getPublicProductId();
        Page<Rating> notApprovedProductRatings = ratingRepository
                .findByProduct_PublicProductId(
                        ratedProductPublicId,
                        PageRequest.of(0, 3)
                );

        // Just checking the size of the returned ratings are 3
        assertEquals("They Are equal",
                notApprovedProductRatings.getContent().size(), 3);
        // check the total pages for the rating is 2
        assertEquals("The total number of ratings is",
                notApprovedProductRatings.getTotalPages(),1);
    }
    @Test
    void should_Return_ARating_With_Given_PublicRatingId_And_PublicRatingId() {

        UUID ratedProductPublicId = productRatings.get(0)
                                .getProduct()
                                .getPublicProductId();
        UUID ratingPublicId = productRatings.get(0)
                                .getRatingPublicId();

        Optional<Rating> fetchedRatingFromDb = ratingRepository
                .findByProduct_PublicProductIdAndRatingPublicId(ratedProductPublicId, ratingPublicId);

        assertEquals("They are equal", fetchedRatingFromDb.get(), productRatings.get(0));
    }
}