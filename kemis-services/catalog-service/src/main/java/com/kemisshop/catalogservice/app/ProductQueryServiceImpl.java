package com.kemisshop.catalogservice.app;

import com.kemisshop.catalogservice.app.port.in.ProductQueryInPort;
import com.kemisshop.catalogservice.app.port.out.ProductQueryOutPort;
import com.kemisshop.catalogservice.app.port.out.RatingQueryOutPort;
import com.kemisshop.catalogservice.domain.Product;
import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/*
    wontgn created on 12/27/20 inside the package - com.kemisshop.catalog.app
*/
@Service
public class ProductQueryServiceImpl implements ProductQueryInPort {

    private final ProductQueryOutPort productQueryOutPort;
    private final RatingQueryOutPort productRatingPort;
    private final DtoEntityMapper mapper;

    public ProductQueryServiceImpl(
            ProductQueryOutPort productQueryOutPort,
            RatingQueryOutPort productRatingPort,
            DtoEntityMapper mapper)
    {
        this.productQueryOutPort = productQueryOutPort;
        this.productRatingPort = productRatingPort;
        this.mapper = mapper;
    }

    @Override
    public Map<String, Object> findOne(UUID publicId) {
        Product product = productQueryOutPort
                .findOne(publicId)
                .orElseThrow(NoSuchElementException::new);

        // Get the first 5 approved ratings with the product

        Map<String, Object> productWithRatings = new HashMap<>();
        productWithRatings.put("product", mapper.toDtoWithLink(product));

        return productWithRatings;

    }

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        return productQueryOutPort
                .findAll(pageable)
                .map(mapper::toDtoWithLink);
    }
}
