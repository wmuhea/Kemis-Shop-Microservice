package com.kemisshop.catalogservice.adapter.persistence;

import com.kemisshop.catalogservice.app.port.out.ModifyRatingOutPort;
import com.kemisshop.catalogservice.app.port.out.RatingQueryOutPort;
import com.kemisshop.catalogservice.domain.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
    wontgn created on 12/27/20 inside the package - com.kemisshop.catalog.adapter.persistence
*/
@Component
public class RatingPersistenceAdapter implements
        RatingQueryOutPort,
        ModifyRatingOutPort {

    private final RatingRepository ratingRepository;

    public RatingPersistenceAdapter(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }



    @Override
    public Page<Rating> readApprovedRatings(UUID publicProductId, Pageable pageable) {
        return ratingRepository
                .findByProduct_PublicProductIdAndApprovedTrue(publicProductId, pageable);

    }

    @Override
    public Page<Rating> readRatingsForApproval(UUID publicProductId, Pageable pageable) {
        return ratingRepository
                .findByProduct_PublicProductIdAndApprovedFalse(publicProductId, pageable);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository
                .save(rating);
    }

    @Override
    public Rating approve(UUID ratingPublicId) {
        return ratingRepository
                .findByRatingPublicId(ratingPublicId);
    }
}
