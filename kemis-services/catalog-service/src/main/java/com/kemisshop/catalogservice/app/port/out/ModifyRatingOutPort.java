package com.kemisshop.catalogservice.app.port.out;

import com.kemisshop.catalogservice.domain.Rating;

import java.util.UUID;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app.port.out
*/
public interface ModifyRatingOutPort {

    Rating save(Rating rating);
    Rating approve(UUID ratingPublicId);

}
