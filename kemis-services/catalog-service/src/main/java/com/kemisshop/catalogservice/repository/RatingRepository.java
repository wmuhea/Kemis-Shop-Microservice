package com.kemisshop.catalogservice.repository;

import com.kemisshop.catalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @HystrixCommand
    Page<Rating> findByProduct_PublicProductIdAndApproved(UUID publicProductId, Boolean approved, Pageable pageable);

    @HystrixCommand
    Page<Rating> findByProduct_PublicProductId(UUID publicProductId, Pageable pageable);

    @HystrixCommand
    Page<Rating> findByProduct_PublicProductIdAndApprovedEquals(UUID publicProductId, Boolean approved, Pageable pageable);

    @HystrixCommand
    Optional<Rating> findByProduct_PublicProductIdAndRatingPublicId(UUID productPublicId, UUID ratingPublicId);
}
