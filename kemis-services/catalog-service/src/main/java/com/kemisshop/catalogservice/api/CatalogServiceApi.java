package com.kemisshop.catalogservice.api;


import com.kemisshop.catalogservice.dto.*;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.model.Category;
import com.kemisshop.catalogservice.model.Product;
import com.kemisshop.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/catalog")
public class CatalogServiceApi {

    private final CatalogService catalogService;
    private final DtoEntityMapper mapper;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public CatalogServiceApi(CatalogService catalogService, DtoEntityMapper mapper,
                             @Qualifier("WebClientLocal") WebClient.Builder builder ) {
        this.catalogService = catalogService;
        this.mapper = mapper;
        this.webClientBuilder = builder;
    }

    @PostMapping(value = "/product", consumes = {"multipart/form-data"})
    public ResponseBean addProduct(@Validated @RequestPart("product") ProductDto productDto, @RequestPart("file") MultipartFile file) {

        // Category Processor get the category from category db
        // why do not I create Product Category here ?
        Category productCategory = Category.findByLabel(productDto.getCategory());

        Product product = mapper.toEntity(productDto);
        ProductDto savedProduct = catalogService.createOne(product, productCategory, file);
        return mapper.toResponseBean(HttpStatus.CREATED, savedProduct);
    }

    @GetMapping("/products")
    public ResponseBean getProductsForDisplay(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {

       Page<ProductDto> productsForDisplay = catalogService.getAll(PageRequest.of(pageNumber, size));
        return  mapper.toResponseBean(HttpStatus.OK, productsForDisplay);

    }

    @GetMapping("/product/{productId}")
    public ResponseBean getProduct(@PathVariable("productId") UUID productPublicId) {

        // Read product with the first 5 ratings and average rating;
        return mapper.toResponseBean(HttpStatus.OK, catalogService.findOne(productPublicId));

    }
    /*
        This getmapping only used for the product images. Due to serialization issues
        it is going to return response entity. Figure out the issue with serialization
        when you want to return response bean.
     */
    @GetMapping("/product/image/{fileName}" )
    public ResponseEntity<?> getProductImage(@PathVariable String fileName) {

        try {
            Resource file = catalogService.loadProductImage(fileName);

            return          ResponseEntity.ok()
                            .contentLength(file.contentLength())
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(new InputStreamResource(file.getInputStream()));


        }catch (IOException ex){
            return new ResponseEntity<>("Couldn't find " + fileName + " => " + ex.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }

    }

    @PostMapping("/product/{productId}/rating")
    public ResponseBean rateProduct(@RequestBody RatingDto ratingDto, @PathVariable UUID productId) {

        ratingDto.setProductPublicId(productId);
        catalogService.rateProduct(mapper.toEntity(ratingDto), ratingDto.getProductPublicId());
        return mapper.toResponseBean(HttpStatus.CREATED,"Your review is created and will be revised");

    }

    @PatchMapping("/product/{publicId}")
    public ResponseBean approveProduct(@PathVariable UUID publicId) {
        catalogService.approveProduct(publicId);
        return mapper.toResponseBean(HttpStatus.ACCEPTED,"The product is approved" );
    }

    @PatchMapping("/product/{productPublicId}/rating/{ratingPublicId}")
    public ResponseBean approveProduct(@PathVariable UUID productPublicId,
                                                 @PathVariable UUID ratingPublicId) {
        catalogService.approveRating(productPublicId, ratingPublicId);
        return mapper.toResponseBean(HttpStatus.OK,"The product is approved");
    }

    @DeleteMapping("/product/{productPublicId}")
    public ResponseBean deleteProduct(@PathVariable UUID productPublicId,
                                      @RequestParam("img") String imageName) throws IOException{
       return
        isProductOrdered(productPublicId) ?
                mapper.toResponseBean(HttpStatus.NOT_ACCEPTABLE, "Please Cancel the Order first") :
                mapper.toResponseBean(HttpStatus.ACCEPTED, catalogService.deleteOne(productPublicId, imageName));

    }

    // This method makes the api call to the order service
    private Boolean isProductOrdered(UUID productPublicId) {

        // BE CAREFUL CHANGE LATTER THE RANDOM UUID WITH THE SELLER ID In the bellew call
       ResponseBean responseBean = webClientBuilder.build()
                                    .get()
                                    .uri("http://order-service/order/"+ UUID.randomUUID()+"/product?id=" + productPublicId)
                                    .retrieve()
                                    .bodyToMono(ResponseBean.class)
                                    .block();
       return responseBean.getResponsePayLoad().equals("Ordered");
    }

}
