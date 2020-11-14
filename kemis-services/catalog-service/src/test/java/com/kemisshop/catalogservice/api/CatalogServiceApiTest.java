package com.kemisshop.catalogservice.api;

import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.dto.ResponseBean;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(CatalogServiceApi.class)
class CatalogServiceApiTest {


    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    CatalogServiceApi serviceApi;

    @Mock
    CatalogService catalogService;

    @Spy
    DtoEntityMapper mapper;

    @Test
    void addProduct() {
    }

    @Test
    void getProductsForDisplay() throws Exception {
        int pageSize = 5;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/catalog/products")
                .accept(MediaType.APPLICATION_JSON);

        Pageable pageable = PageRequest.of(0,pageSize);
        List<ProductDto> pDto =
                TestEntitiesFactory.buildProductEntityList()
                .stream()
                .map(mapper::toDtoWithLink)
                .collect(Collectors.toList());

        int returnedPageSize = (pDto.size() > 5 ? 5 : pDto.size());
        Page<ProductDto> page = new PageImpl<ProductDto>(pDto.subList(0, returnedPageSize), pageable, pDto.size());
        when(catalogService.getAll(any())).thenReturn(page);

        ResponseBean responseBean = mapper.toResponseBean(HttpStatus.OK, catalogService.getAll(pageable));
        mockMvc.perform(requestBuilder)
        .andReturn(); // The perform method throws exception
    }

    @Test
    void getProduct() {
    }

    @Test
    void getProductImage() {
    }

    @Test
    void rateProduct() {
    }

    @Test
    void approveProduct() {
    }

    @Test
    void testApproveProduct() {
    }

    @Test
    void deleteProduct() {
    }
}