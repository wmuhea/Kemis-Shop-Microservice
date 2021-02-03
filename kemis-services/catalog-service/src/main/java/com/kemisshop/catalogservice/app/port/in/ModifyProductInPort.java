package com.kemisshop.catalogservice.app.port.in;

import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ModifyProductInPort {

    ProductDto save(Product product, Category category, MultipartFile file);
    ProductDto update(Product product, UUID publicId);
    String delete(UUID publicId) throws IOException;
    void updateUnitsInStock(UUID productPublicId);
    void approveProduct(UUID publicProductId);


}
