package com.kemisshop.catalogservice.app;

import com.kemisshop.catalogservice.Util.ImageProcessor;
import com.kemisshop.catalogservice.app.port.in.ModifyProductInPort;
import com.kemisshop.catalogservice.app.port.out.CategoryQueryOutPort;
import com.kemisshop.catalogservice.app.port.out.ModifyCategoryOutPort;
import com.kemisshop.catalogservice.app.port.out.ModifyProductOutPort;
import com.kemisshop.catalogservice.app.port.out.ProductQueryOutPort;
import com.kemisshop.catalogservice.domain.Product;
import com.kemisshop.catalogservice.domain.ProductCategory;
import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class ModifyProductServiceImplTest {

    @Mock
    ModifyProductOutPort modifyProductOutPort;
    @Mock
    ProductQueryOutPort productQueryOutPort;
    @Mock
    CategoryQueryOutPort loadCategoryPort;
    @Mock
    ModifyCategoryOutPort updateCategoryPort;
    @Spy
    DtoEntityMapper mapper;
    @Mock
    ImageProcessor imageUtil;

    @InjectMocks
    ModifyProductInPort modifyProductInPort;

    @Test
    void save() {

        ProductCategory category = TestEntitiesFactory.buildProductCategory();
        Product product = TestEntitiesFactory.buildProductEntity();
        String imageName = product.getImageName() + product.getPublicProductId() + ".jpg";
        MockMultipartFile image = new MockMultipartFile("file", imageName, String.valueOf(MediaType.IMAGE_JPEG), new byte[10]);

        doReturn(imageName).when(imageUtil.storeImageIntoDisk(image, product));
        doReturn(product).when(category.addProduct(product));
        doReturn(product).when(updateCategoryPort.save(category));
        doReturn(product).when(modifyProductOutPort.save(product));
        ProductDto expected = mapper.toDtoWithLink(product);

        ProductDto actual = modifyProductInPort.save(product, category.getCategory(), image);

        assertAll("produuct",
                () -> assertEquals(expected.getName(), equals(actual.getName())),
                () ->  assertEquals(expected.getPrice(), actual.getPrice()),
                () -> assertEquals(expected.getLink("self"), actual.getLink("self"))
                );

    }

    @Test
    void update() {

        Product savedProduct = TestEntitiesFactory.buildProductEntity();
        Product updatedProduct = TestEntitiesFactory.buildUpdatedProductEntity();
        updatedProduct.setPublicProductId(savedProduct.getPublicProductId());

        doReturn(updatedProduct).when(
                productQueryOutPort.findOne(savedProduct.getPublicProductId())
        );

        doReturn(updatedProduct).when(
                modifyProductOutPort.save(savedProduct)
        );

        ProductDto expectedProductDto = mapper.toDtoWithLink(updatedProduct);
        ProductDto actualUpdatedProduct =
                modifyProductInPort
                        .update(updatedProduct, savedProduct.getPublicProductId());

        assertAll("Update Product",
                () -> assertEquals(expectedProductDto.getName(), actualUpdatedProduct.getName()),
                () -> assertEquals(expectedProductDto.getPrice(), actualUpdatedProduct.getPrice()),
                () -> assertEquals(expectedProductDto.getUnitsInStock(), actualUpdatedProduct.getUnitsInStock())
        );

    }

    @Test
    void delete() {

        int [] charArr = new int[26];
        Arrays.fill(charArr, 0);
        String s1 = "ene";
        String s2 = "nee";
        Boolean result = true;

        for(int i = 0; i < s1.length(); i++) {
            charArr[s1.charAt(i) - 'a']--;
        }

        for(int i = 0; i < s2.length(); i++) {
            charArr[s2.charAt(i) - 'a']++;
        }

        for(int a : charArr) {
            if(a != 0) {
                result = false;
            }
        }


    }

    @Test
    void updateUnitsInStock() {
    }

    @Test
    void approveProduct() {
    }
}