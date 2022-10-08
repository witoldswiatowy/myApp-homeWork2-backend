package com.project.spring.controller;

import com.project.spring.component.PrincipalComponent;
import com.project.spring.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 18.09.2022
 */
class ProductControllerTest {

    ProductService productService;          // mock
    PrincipalComponent principalComponent;          // mock
    ProductController productController;

    @BeforeEach
    public void prepareEnvironment(){
        productService = mock(ProductService.class);
        principalComponent = mock(PrincipalComponent.class);
        productController = new ProductController(productService, principalComponent);
    }

    @Test
    // TODO: check ParametrizedTest
    void testGetAllUserProductsCallsWithUserMethod(){
        final Long userId = 1L;
        final Integer page = 0;
        final Integer size = 10;

        // ignoruje wyjście
        productController.getAllUserProducts(userId, page, size);

        verify(productService, times(1))
                .getUserProducts(userId, PageRequest.of(page, size));
        verify(productService, never()).getAllProducts(any());
    }

    @Test
    void testGetAllUserProductsCallsWithoutUserMethod(){
        final Integer page = 0;
        final Integer size = 10;

        // ignoruje wyjście
        productController.getAllUserProducts(null, page, size);

        verify(productService, never()).getUserProducts(any(), any());
        verify(productService, times(1)).getAllProducts(PageRequest.of(page, size));
    }

}