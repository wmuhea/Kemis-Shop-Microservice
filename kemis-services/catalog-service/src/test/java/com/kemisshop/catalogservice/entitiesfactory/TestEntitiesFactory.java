package com.kemisshop.catalogservice.entitiesfactory;

import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.model.ProductCategory;
import com.kemisshop.catalogservice.model.Rating;

import java.math.BigDecimal;
import java.util.*;

public class TestEntitiesFactory {

    public static ProductCategory buildProductCategory() {
       return new ProductCategory(Category.Mekenet);

    }
    public static Product buildProductEntity() {

        ProductCategory pc = buildProductCategory();
        BigDecimal price = BigDecimal.valueOf(60L);
        return Product.builder()
                .category(pc)
                .name("Yegojam Kemis")
                .publicProductId(UUID.randomUUID())
                .description("It is from dembecha Gojam yebahil libs")
                .publicSellerId(UUID.randomUUID())
                .imageName("Ysew Kemis")
                .unitsInStock(58)
                .price(price)
                .build();
    }

    public static List<Product> buildProductEntityList() {
        return Arrays.asList(
                Product.builder()
                        .category(buildProductCategory())
                        .name("Yegojam Kemis")
                        .publicProductId(UUID.randomUUID())
                        .description("It is from dembecha Gojam yebahil libs")
                        .publicSellerId(UUID.randomUUID())
                        .imageName("Ylij Kemis")
                        .unitsInStock(58)
                        .price(BigDecimal.valueOf(60L))
                        .build(),

                Product.builder()
                        .category(buildProductCategory())
                        .name("Yegonder Kemis")
                        .publicProductId(UUID.randomUUID())
                        .description("It is from Azebo ketema")
                        .publicSellerId(UUID.randomUUID())
                        .imageName("Yewetat sew Kemis")
                        .unitsInStock(55)
                        .price(BigDecimal.valueOf(12L))
                        .build(),

                Product.builder()
                        .category(buildProductCategory())
                        .name("Yeraya Kemis")
                        .publicProductId(UUID.randomUUID())
                        .description("It is from Kobo woreda")
                        .publicSellerId(UUID.randomUUID())
                        .imageName("Ye tilik setoch Kemis")
                        .unitsInStock(85)
                        .price(BigDecimal.valueOf(160L))
                        .build()
        );
    }

    public static List<Rating> buildRatingEntityList() {

        Product productToBeRated = buildProductEntity();
        List<Rating> testRatings = new ArrayList<>();
        return Arrays.asList(
                Rating.builder()
                        .buyerPublicId(UUID.randomUUID())
                        .product(productToBeRated)
                        .ratingPublicId(UUID.randomUUID())
                        .rating(5)
                        .review("Interesting product and satisfied")
                        .build(),
                Rating.builder()
                        .buyerPublicId(UUID.randomUUID())
                        .product(productToBeRated)
                        .ratingPublicId(UUID.randomUUID())
                        .rating(2)
                        .review("Arrived Damaged")
                        .build(),
                Rating.builder()
                        .buyerPublicId(UUID.randomUUID())
                        .product(productToBeRated)
                        .ratingPublicId(UUID.randomUUID())
                        .rating(5)
                        .review("Best product ever bought")
                        .build()
        );

    }
}
