package com.kemisshop.catalogservice.adapter.api;

import com.kemisshop.catalogservice.app.port.in.ModifyProductInPort;
import com.kemisshop.catalogservice.app.port.in.ModifyRatingInPort;
import com.kemisshop.catalogservice.app.port.in.RatingQueryInPort;
import com.kemisshop.catalogservice.dto.RatingDto;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.mapper.ResponseBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
    wontgn created on 12/28/20 inside the package - com.kemisshop.catalog.adapter.api
*/
@RestController("/ratings")
public class RatingApi {

    private final ModifyRatingInPort ratingModifyService;
    private final RatingQueryInPort ratingQueryService;
    private final ModifyProductInPort productModifyService;
    private final DtoEntityMapper mapper;

    public RatingApi(ModifyRatingInPort ratingModifyService,
                     RatingQueryInPort ratingQueryService,
                     ModifyProductInPort productModifyService,
                     DtoEntityMapper mapper)
    {
        this.ratingModifyService = ratingModifyService;
        this.ratingQueryService = ratingQueryService;
        this.productModifyService = productModifyService;
        this.mapper = mapper;
    }

    @PostMapping("/{productId}")
    public ResponseBean rateProduct(
            @RequestBody RatingDto ratingDto,
            @PathVariable UUID productId)
    {

        ratingDto.setProductPublicId(productId);
        ratingModifyService
                .rateProduct(mapper.toEntity(ratingDto), ratingDto.getProductPublicId());
        return mapper
                .toResponseBean(HttpStatus.CREATED,"Your review is created and will be revised");

    }

    @PatchMapping("/all")
    public ResponseBean approveRating(
            @RequestParam("pPid") UUID productPublicId,
            @RequestParam("rPid") UUID ratingPublicId) {
        ratingModifyService.approveRating(ratingPublicId);
        return mapper.toResponseBean(HttpStatus.OK,"The product is approved");
    }


    @GetMapping("/pPid")
    public ResponseBean getRatings(
            @RequestParam("status") String approvalStatus,
            @RequestParam("pNo") Integer pageNumber,
            @RequestParam("pSize") Integer sizePerPage,
            @PathVariable("pPid") UUID publicProductId)
    {
        Page<RatingDto> pageOfRatings = null;
        if(approvalStatus.equals("approved"))
            pageOfRatings = ratingQueryService
                .readApprovedRatings(publicProductId, PageRequest.of(pageNumber, sizePerPage));
        else {
            pageOfRatings = ratingQueryService
                    .readRatingsForApproval(publicProductId,PageRequest.of(pageNumber, sizePerPage));
        }

        return mapper.toResponseBean(HttpStatus.OK, pageOfRatings);
    }
}
