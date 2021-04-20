package com.kemisshop.catalogservice.adapter.persistence;

import com.kemisshop.catalogservice.domain.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Page<Rating> findByProduct_PublicProductIdAndApprovedTrue(UUID publicProductId, Pageable pageable);

    Rating findByRatingPublicId(UUID ratingPublicId);

    Page<Rating> findByProduct_PublicProductIdAndApprovedFalse(UUID publicProductId, Pageable pageable);
}
