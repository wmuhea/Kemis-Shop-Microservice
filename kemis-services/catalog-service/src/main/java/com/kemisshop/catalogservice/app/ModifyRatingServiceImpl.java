package com.kemisshop.catalogservice.app;

import com.kemisshop.catalogservice.app.port.in.ModifyRatingInPort;
import com.kemisshop.catalogservice.app.port.out.ModifyRatingOutPort;
import com.kemisshop.catalogservice.app.port.out.ProductQueryOutPort;
import com.kemisshop.catalogservice.domain.Product;
import com.kemisshop.catalogservice.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app
*/
@Service
public class ModifyRatingServiceImpl implements ModifyRatingInPort {

    private final ModifyRatingOutPort ratingPersistencePort;
    private final ProductQueryOutPort productPersistencePort;


    public ModifyRatingServiceImpl(
            ModifyRatingOutPort ratingPersistencePort,
            ProductQueryOutPort productPersistencePort )
    {
        this.ratingPersistencePort = ratingPersistencePort;
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public void approveRating(UUID ratingProductId) {
            ratingPersistencePort
                    .approve(ratingProductId);
    }

    @Override
    public Rating rateProduct(Rating rating, UUID productPublicId) {
        // fetch product and set it
        Product importedProduct = productPersistencePort
                .findOne(productPublicId)
                .orElseThrow(NoSuchElementException::new);

        rating.setProduct(importedProduct);
        rating.setRatingPublicId(UUID.randomUUID());

        return ratingPersistencePort
                .save(rating);

    }
}
