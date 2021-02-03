package com.kemisshop.catalogservice.app.port.in;

import com.kemisshop.catalogservice.dto.RatingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RatingQueryInPort {

    Page<RatingDto> readApprovedRatings(UUID publicProductId, Pageable pageable);
    Page<RatingDto> readRatingsForApproval(UUID publicProductId, Pageable pageable);
}
