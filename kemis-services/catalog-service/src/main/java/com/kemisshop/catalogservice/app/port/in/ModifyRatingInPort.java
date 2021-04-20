package com.kemisshop.catalogservice.app.port.in;

import com.kemisshop.catalogservice.domain.Rating;

import java.util.UUID;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app.port.in
*/
public interface ModifyRatingInPort {

    Rating rateProduct(Rating rating, UUID productPublicId);
    void approveRating(UUID ratingProductId);
}
