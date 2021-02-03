package com.kemisshop.catalogservice.app.port.in;

import com.kemisshop.catalogservice.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface ProductQueryInPort {
    Map<String, Object> findOne(UUID publicId);
    Page<ProductDto> getAll(Pageable pageable);
}
