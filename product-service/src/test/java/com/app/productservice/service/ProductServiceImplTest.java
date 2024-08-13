package com.app.productservice.service;

import com.app.productservice.exception.ProductAlreadyExistsException;
import com.app.productservice.exception.ProductNotFoundException;
import com.app.productservice.model.dto.ProductCreateRequest;
import com.app.productservice.model.dto.ProductDto;
import com.app.productservice.model.dto.ProductResponseDto;
import com.app.productservice.model.entity.Product;
import com.app.productservice.model.enums.ProductUnit;
import com.app.productservice.model.mapper.ProductMapper;
import com.app.productservice.repository.ProductRepository;
import com.app.productservice.service.impl.ProductServiceImpl;
import com.app.productservice.testutils.ProductTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductServiceImpl productServiceImpl;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productMapper = mock(ProductMapper.class);
        restTemplate = mock(RestTemplate.class);

        productServiceImpl = new ProductServiceImpl(productRepository, productMapper, restTemplate);
    }

    @Test
    public void deleteProductTest() {
        Product product = ProductTestUtil.createProduct("ME001", "testName", "MEYVE", "testBrand", ProductUnit.KILOGRAM);
        when(productRepository.findById("ME001")).thenReturn(Optional.of(product));
        ProductResponseDto productResponseDto = ProductTestUtil.createProductResponseDto(new ArrayList<ProductDto>(), HttpStatus.OK.toString(), true);
        ProductResponseDto result = productServiceImpl.deleteProduct("ME001");
        assertEquals(productResponseDto, result);
        verify(productRepository).findById("ME001");
        verify(productRepository).deleteById("ME001");
    }

    @Test
    public void deleteProductTest_shouldThrowProductNotFoundException_whenProductNotFound() {
        when(productRepository.findById("ME001")).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.deleteProduct("ME001"));
    }

    @Test
    public void getProductTest() {
        Product product = ProductTestUtil.createProduct("ME001", "testName", "MEYVE", "testBrand", ProductUnit.KILOGRAM);
        when(productRepository.findById("ME001")).thenReturn(Optional.of(product));
        ProductDto productDto = ProductTestUtil.createProductDto(product);
        when(productMapper.toProductDto(product)).thenReturn(productDto);

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);
        ProductResponseDto productResponseDto = ProductTestUtil.createProductResponseDto(productDtoList, HttpStatus.OK.toString(), true);

        ProductResponseDto result = productServiceImpl.getProduct("ME001");

        assertEquals(productResponseDto, result);
    }

    @Test
    public void getProductTest_shouldThrowProductNotFoundException_whenProductNotFound() {
        when(productRepository.findById("ME001")).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.getProduct("ME001"));
    }

    @Test
    public void createProductTest_shouldThrowInputMismatchException_whenProductCodeHasMoreThanThreeDigits() {
        ProductCreateRequest productCreateRequest = ProductTestUtil.createProductCreateRequest(1000,
                "testName", "MEYVE", "testBrand", "KILOGRAM");
        assertThrows(InputMismatchException.class, () -> productServiceImpl.createProduct(productCreateRequest));
        verifyNoInteractions(productRepository);
        verifyNoInteractions(productMapper);
    }

    @Test
    public void createProductTest_shouldThrowProductAlreadyExistsException_whenProductAlreadyExists() {
        ProductCreateRequest productCreateRequest = ProductTestUtil.createProductCreateRequest(1,
                "testName", "MEYVE", "testBrand", "KILOGRAM");
        Product product = ProductTestUtil.createProduct("ME001", "testName", "MEYVE", "testBrand", ProductUnit.KILOGRAM);
        when(productRepository.findByProductName("testName")).thenReturn(Optional.of(product));
        assertThrows(ProductAlreadyExistsException.class, () -> productServiceImpl.createProduct(productCreateRequest));
        verifyNoInteractions(productMapper);
    }



}
