package com.kemisshop.catalogservice.app;


import com.kemisshop.catalogservice.Util.ImageProcessor;
import com.kemisshop.catalogservice.app.port.in.ModifyProductInPort;
import com.kemisshop.catalogservice.app.port.out.ModifyCategoryOutPort;
import com.kemisshop.catalogservice.app.port.out.ModifyProductOutPort;
import com.kemisshop.catalogservice.app.port.out.CategoryQueryOutPort;
import com.kemisshop.catalogservice.app.port.out.ProductQueryOutPort;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.Util.ImageUtil;
import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.Product;
import com.kemisshop.catalogservice.domain.ProductCategory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ExecutionException;


@Service
@Transactional
public class ModifyProductServiceImpl implements ModifyProductInPort {

    private final ModifyProductOutPort modifyProductOutPort;
    private final ProductQueryOutPort productQueryOutPort;
    private final CategoryQueryOutPort loadCategoryPort;
    private final ModifyCategoryOutPort updateCategoryPort;
    private final DtoEntityMapper mapper;
    private final ImageProcessor imageUtil;

    @Autowired
    public ModifyProductServiceImpl(ModifyProductOutPort modifyProductOutPort,
                                    ProductQueryOutPort productQueryOutPort,
                                    CategoryQueryOutPort loadCategoryPort,
                                    ModifyCategoryOutPort updateCategoryPort,
                                    ImageProcessor imageUtil,
                                    DtoEntityMapper mapper) {

        this.modifyProductOutPort = modifyProductOutPort;
        this.productQueryOutPort = productQueryOutPort;
        this.loadCategoryPort = loadCategoryPort;
        this.updateCategoryPort = updateCategoryPort;
        this.imageUtil = imageUtil;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProductDto save(Product product, Category category,
            MultipartFile file)  throws NoSuchElementException {

        ProductCategory productCategory = loadCategoryPort.findOne(category)
                .orElseThrow(NoSuchElementException::new);

        product.setPublicProductId(UUID.randomUUID());
        product.setImageName(imageUtil.storeImageIntoDisk(file, product));
        productCategory.addProduct(product);
        updateCategoryPort.save(productCategory);
        Product savedProduct = modifyProductOutPort.save(product);
        return mapper.toDtoWithLink(savedProduct);
    }

    @Override
    public ProductDto update(Product product, UUID publicId) {

        Product productReadFromDb = productQueryOutPort
                .findOne(publicId)
                .orElseThrow(NoSuchElementException::new);

        productReadFromDb.setName(product.getName());
        productReadFromDb.setDescription(product.getDescription());
        productReadFromDb.setPrice(product.getPrice());
        productReadFromDb.setUnitsInStock(product.getUnitsInStock());
        return mapper.toDtoWithLink(modifyProductOutPort.save(productReadFromDb));
    }


    @Override
    // Example code of using circuit breaker for database calls
    @Transactional
    @HystrixCommand(
            commandProperties =
                    {@HystrixProperty(
                            name = "execution.isolation.thread.timeOutInMilliSeconds",
                            value = "12000"
                    )})
    public String delete(UUID publicId) {
        Product product = modifyProductOutPort
                .deleteOne(publicId)
                .orElseThrow(NoSuchElementException::new);
        imageUtil.deleteProductImage(product.getImageName());
        return "Your product is deleted";
    }


    @Override
    public void updateUnitsInStock(UUID productPublicId) {
        Product purchasedProduct = productQueryOutPort
                .findOne(productPublicId)
                .orElseThrow(NoSuchElementException::new);

        Integer updateUnitsInStock = purchasedProduct.getUnitsInStock() - 1;
        purchasedProduct.setUnitsInStock(updateUnitsInStock);
        modifyProductOutPort.save(purchasedProduct);

    }

    @Override
    public void approveProduct(UUID publicProductId) {
        Product productTobeApproved =
                productQueryOutPort
                .findOne(publicProductId)
                .orElseThrow(NoSuchElementException::new);

        productTobeApproved
                .approve();
        modifyProductOutPort
                .save(productTobeApproved);
    }


}

