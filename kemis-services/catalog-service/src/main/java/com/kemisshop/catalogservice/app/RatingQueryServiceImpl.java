package com.kemisshop.catalogservice.app;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.app
*/

import com.kemisshop.catalogservice.app.port.in.RatingQueryInPort;
import com.kemisshop.catalogservice.app.port.out.RatingQueryOutPort;
import com.kemisshop.catalogservice.dto.RatingDto;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RatingQueryServiceImpl implements RatingQueryInPort {

    private final RatingQueryOutPort ratingLoadPersistencePort;
    private final DtoEntityMapper mapper;

    public RatingQueryServiceImpl(
            RatingQueryOutPort ratingLoadPersistencePort,
            DtoEntityMapper mapper)
    {
        this.ratingLoadPersistencePort = ratingLoadPersistencePort;
        this.mapper = mapper;
    }

    @Override
    public Page<RatingDto> readApprovedRatings(UUID publicProductId, Pageable pageable) {
        return ratingLoadPersistencePort
                .readApprovedRatings(publicProductId, pageable)
                 .map(mapper::toDto);
    }

    @Override
    public Page<RatingDto> readRatingsForApproval(UUID publicProductId, Pageable pageable) {
        return ratingLoadPersistencePort
                .readRatingsForApproval(publicProductId, pageable)
                .map(mapper::toDto);
    }
}
