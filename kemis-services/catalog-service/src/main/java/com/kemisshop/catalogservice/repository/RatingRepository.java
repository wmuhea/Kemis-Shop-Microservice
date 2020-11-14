package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Page<Rating> findByProduct_PublicProductIdAndApproved(UUID publicProductId, Boolean approved, Pageable pageable);

    Page<Rating> findByProduct_PublicProductId(UUID publicProductId, Pageable pageable);

    Page<Rating> findByProduct_PublicProductIdAndApprovedEquals(UUID publicProductId, Boolean approved, Pageable pageable);

    Optional<Rating> findByProduct_PublicProductIdAndRatingPublicId(UUID productPublicId, UUID ratingPublicId);
}
