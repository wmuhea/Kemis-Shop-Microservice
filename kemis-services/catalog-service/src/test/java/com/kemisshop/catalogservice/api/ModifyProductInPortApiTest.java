package com.kemisshop.catalogservice.api;

import com.kemisshop.catalogservice.adapter.api.CatalogApi;
import com.kemisshop.catalogservice.app.port.in.ProductQueryInPort;
import com.kemisshop.catalogservice.dto.ProductDto;
import com.kemisshop.catalogservice.mapper.ResponseBean;
import com.kemisshop.catalogservice.entitiesfactory.TestEntitiesFactory;
import com.kemisshop.catalogservice.mapper.DtoEntityMapper;
import com.kemisshop.catalogservice.app.port.in.ModifyProductInPort;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@WebMvcTest(CatalogApi.class)
class ModifyProductInPortApiTest {


    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    CatalogApi serviceApi;

    @Mock
    ModifyProductInPort modifyProductInPort;

    @Mock
    ProductQueryInPort productQueryInPort;

    @Spy
    DtoEntityMapper mapper;

    @Test
    void addProduct() {
    }

    @Test
    void getProductsForDisplay() throws Exception {
        int pageSize = 5;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .accept(MediaType.APPLICATION_JSON);

        Pageable pageable = PageRequest.of(0,pageSize);
        List<ProductDto> pDto =
                TestEntitiesFactory.buildProductEntityList()
                .stream()
                .map(mapper::toDtoWithLink)
                .collect(Collectors.toList());

        int returnedPageSize = (pDto.size() > 5 ? 5 : pDto.size());
        Page<ProductDto> products =
                new PageImpl<ProductDto>(pDto.subList(0, returnedPageSize), pageable, pDto.size());

        doReturn(products).when(productQueryInPort).getAll(pageable);
        ResponseBean responseBean = mapper.toResponseBean(HttpStatus.OK, productQueryInPort.getAll(pageable));
        mockMvc.perform(requestBuilder)
        .andReturn();
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