package com.kemisshop.catalogservice.Util;

import com.kemisshop.catalogservice.exceptions.FileStorageException;
import com.kemisshop.catalogservice.domain.Product;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUtil {

    private static final String  UPLOAD_DIR = "kemis-services/product-images";
    private final ResourceLoader resourceLoader;

    public ImageUtil(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource loadProductImage(String fileName) {
        return resourceLoader.getResource("file:"+ UPLOAD_DIR + "/" + fileName);
    }


    public void deleteProductImage(String productImageName) {
        try {
            Files.delete(Paths.get(UPLOAD_DIR, productImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String storeProductImage(MultipartFile file, Product product) {
        // The image file name will be unique as it is set to product name + public Seller Id
        String fileName = product.getName() + "_" + product.getPublicProductId().toString() + ".jpg";

        if (!file.isEmpty()) {

            try (InputStream inputStream = file.getInputStream()) {
                // Check file name
                Files.copy(inputStream, Paths.get(UPLOAD_DIR, fileName), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
            }

        }

        return fileName;
    }
}