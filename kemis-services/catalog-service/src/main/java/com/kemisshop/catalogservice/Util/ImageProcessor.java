package com.kemisshop.catalogservice.Util;

import com.kemisshop.catalogservice.app.port.in.ImageProcessingUseCase;

import com.kemisshop.catalogservice.domain.Product;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.*;

/*
    wontgn created on 12/30/20 inside the package - com.kemisshop.catalogservice.Util
*/
@Component
public class ImageProcessor implements ImageProcessingUseCase {

    private final ImageUtil imageUtil;
    private final ExecutorService executor;

    public ImageProcessor(ImageUtil imageUtil, ExecutorService executor) {
        this.imageUtil = imageUtil;
        this.executor = executor;
    }

    /**
     * needs improvement for better implementation of multithreading
     * @param imageFileName
     * @return
     */
    public Resource loadImage(String imageFileName) {

        Resource productImage = null;
        Callable<Resource> imageLoadingTask =
                () -> imageUtil.loadProductImage(imageFileName);

        Future<Resource> futureImage = executor.submit(imageLoadingTask);

        try {
            productImage = futureImage.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();

        }
        return productImage;
    }

    public String storeImageIntoDisk(MultipartFile file, Product product) {

        String imageName = null;
        Callable<String> storeImageIntoDiskTask =
                () -> imageUtil.storeProductImage(file, product);

        Future<String> storedImageName = executor.submit(storeImageIntoDiskTask);


        try {
            imageName = storedImageName.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return  imageName;
    }

    public void deleteProductImage(String productImageName) {

        Runnable deleteProductImageTask =
                () -> imageUtil.deleteProductImage(productImageName);

        executor.execute(deleteProductImageTask);

    }

}
