package com.kemisshop.catalogservice.mapper;

import com.kemisshop.catalogservice.adapter.api.CatalogApi;
import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.dto.RatingDto;
import com.kemisshop.catalogservice.domain.Product;
import com.kemisshop.catalogservice.domain.ProductCategory;
import com.kemisshop.catalogservice.domain.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface DtoEntityMapper {


    @Mapping(target = "ratingPublicId", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    Rating toEntity(RatingDto ratingDto);


    @Mapping(target = "productPublicId", ignore = true)
    RatingDto toDto(Rating productRating);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "averageRating", ignore = true)
    @Mapping(target = "imageName", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDto productDto);


    ProductDto toDto(Product product);

    default ResponseBean toResponseBean(HttpStatus responseStatus, Object object) {
        return new ResponseBean(responseStatus.name(), object);
    }

    default ResponseBean toResponseBean(HttpStatus responseStatus, Page<?> responseContent) {
        return new ResponseBean(responseStatus.name(), responseContent.getTotalPages(), responseContent.getContent());
    }

//    Set<RatingDto> toDtoSet(Set<Rating> ratings);


    default String toCategoryName(ProductCategory productCategory) {
        return  productCategory.getCategory().name();

    }

    default ProductDto toDtoWithLink(Product product) {
        String imageName = product.getImageName();
        ProductDto productDto = this.toDto(product);
        UUID publicId = productDto.getPublicProductId();
        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(CatalogApi.class).getProductImage(imageName)).withRel("image");
        Link imageLink = linkTo(methodOn(CatalogApi.class).getProduct(publicId)).withSelfRel();
        productDto.add(imageLink);
        productDto.add(selfLink);
        return productDto;
    }


}
