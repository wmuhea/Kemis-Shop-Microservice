package com.kemisshop.catalogservice.entitiesfactory;

import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.Product;
import com.kemisshop.catalogservice.domain.ProductCategory;
import com.kemisshop.catalogservice.domain.Rating;

import java.math.BigDecimal;
import java.util.*;

public class TestEntitiesFactory {

    public static ProductCategory buildProductCategory() {
       return new ProductCategory(Category.MEKENET);
    }
    public static List<ProductCategory> buildProductCategoryList() {
        return  Arrays.asList(new ProductCategory[]{
                    new ProductCategory(Category.MEKENET),
                    new ProductCategory(Category.KEMIS),
                    new ProductCategory(Category.TIGRE),
                    new ProductCategory(Category.RAYA)
                }
        );
    }
    public static Product buildProductEntity() {

        ProductCategory pc = buildProductCategory();
        BigDecimal price = BigDecimal.valueOf(60L);
        UUID testProductID = UUID.randomUUID();
        UUID testSellerID = UUID.randomUUID();
        String testProductImageName = "Yegojam Kemis_"  + testProductID + ".jpg";

        return Product.builder()
                .name("Yegojam Kemis")
                .publicProductId(testProductID)
                .description("It is from dembecha Gojam yebahil libs")
                .publicSellerId(testSellerID)
                .imageName(testProductImageName)
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

    public static ProductDto generateProductDtoFromTestProduct(Product product) {
        return ProductDto.builder()
                .category(product.getCategory().getCategory().name())
                .price(product.getPrice())
                .description(product.getDescription())
                .publicProductId(product.getPublicProductId())
                .publicSellerId(product.getPublicSellerId())
                .imageName(product.getImageName())
                .name(product.getName())
                .unitsInStock(product.getUnitsInStock())
                .build();
    }

    public static Product buildUpdatedProductEntity() {

        ProductCategory pc = buildProductCategory();
        BigDecimal price = BigDecimal.valueOf(65L);
        UUID testProductID = UUID.randomUUID();
        UUID testSellerID = UUID.randomUUID();
        String testProductImageName = "Yegonder Kemis"  + testProductID + ".jpg";

        return Product.builder()
                .name("Yegonder Kemis")
                .publicProductId(testProductID)
                .description("It is from Gonder yebahil libs")
                .publicSellerId(testSellerID)
                .imageName(testProductImageName)
                .unitsInStock(78)
                .price(price)
                .build();
    }
}

