package com.kemisshop.catalogservice.app.port.out;

import com.kemisshop.catalogservice.domain.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app.port.out
*/
public interface RatingQueryOutPort {

    Page<Rating> readApprovedRatings(UUID publicProductId, Pageable pageable);

    Page<Rating> readRatingsForApproval(UUID publicProductId, Pageable pageable);

}
