package com.app.productservice.service.impl;

import com.app.productservice.exception.ProductAlreadyExistsException;
import com.app.productservice.exception.ProductNotFoundException;
import com.app.productservice.model.dto.*;
import com.app.productservice.model.entity.Product;
import com.app.productservice.model.mapper.ProductMapper;
import com.app.productservice.repository.ProductRepository;
import com.app.productservice.service.ProductService;
import com.app.productservice.service.strategy.ProductUnitStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final RestTemplate restTemplate;
    private static final HashMap<String, ProductUnitStrategy>  PRODUCT_UNIT_STRATEGY_HASH_MAP = new HashMap<>();

    public static void addProductUnitStrategy(String productUnit, ProductUnitStrategy productUnitStrategy) {
        PRODUCT_UNIT_STRATEGY_HASH_MAP.put(productUnit, productUnitStrategy);
    }

    @Override
    public ProductResponseDto createProduct(ProductCreateRequest productCreateRequest) {
        if (productCreateRequest.getProductCode() >= 1000) {
            throw new InputMismatchException("Product code must have 3 digits");
        }
        Optional<Product> optionalProduct = productRepository.findByProductName(productCreateRequest.getProductName());

        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product with given Product Name already exists");
        }

        ProductUnitStrategy productUnitStrategy = PRODUCT_UNIT_STRATEGY_HASH_MAP.get(productCreateRequest.getProductUnit());

        if (productUnitStrategy == null) {
            throw new InputMismatchException("Product Unit must be either PIECE or KILOGRAM");
        }

        String categoryUrl = "http://localhost:8081/v1/api/category" + "?name=" + productCreateRequest.getCategoryName();

        try {
            CategoryDto categoryDto = restTemplate.getForObject(categoryUrl, CategoryDto.class);

            if(categoryDto == null) {
                throw new ProductNotFoundException("Category not found");
            }
            String productCode = String.format("%s%03d", categoryDto.getCategoryCode(), productCreateRequest.getProductCode());
            optionalProduct = productRepository.findById(productCode);

            if (optionalProduct.isPresent()) {
                throw new ProductAlreadyExistsException("Product with given Product Code already exists");
            }

            Product product = Product.builder()
                    .productCode(productCode)
                    .productName(productCreateRequest.getProductName())
                    .categoryName(categoryDto.getName())
                    .brand(productCreateRequest.getBrand())
                    .productUnit(productUnitStrategy.getProductUnit())
                    .build();

            ProductDto productDto = productMapper.toProductDto(productRepository.save(product));
            List<ProductDto> productDtoList = new ArrayList<>();
            productDtoList.add(productDto);

            return ProductResponseDto.builder()
                    .productDtoList(productDtoList)
                    .message(HttpStatus.CREATED.toString())
                    .isSuccess(true)
                    .build();

        } catch (HttpClientErrorException e) {
            return ProductResponseDto.builder()
                    .productDtoList(new ArrayList<>())
                    .message(e.getResponseBodyAsString())
                    .isSuccess(false)
                    .build();
        }
    }

    @Override
    public ProductResponseDto getProduct(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found.");
        }
        ProductDto productDto = productMapper.toProductDto(optionalProduct.get());
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);

        return ProductResponseDto.builder()
                .productDtoList(productDtoList)
                .message(HttpStatus.OK.toString())
                .isSuccess(true)
                .build();
    }

    @Override
    public ProductResponseDto deleteProduct(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found.");
        }
        productRepository.deleteById(id);

        String barcodeUrl = "http://localhost:8080/v1/api/barcode" + "?productCode=" + id;
        try {
            restTemplate.delete(barcodeUrl);
        } catch (HttpClientErrorException e) {
            return ProductResponseDto.builder()
                    .productDtoList(new ArrayList<>())
                    .message(e.getMessage())
                    .isSuccess(false)
                    .build();
        }
        return ProductResponseDto.builder()
                .productDtoList(new ArrayList<>())
                .message(HttpStatus.OK.toString())
                .isSuccess(true)
                .build();
    }

    @Override
    public ProductResponseDto updateProduct(String id, ProductUpdateRequest productUpdateRequest) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found.");
        }

        Product product = optionalProduct.get();

        Optional<Product> optional = productRepository.findByProductName(productUpdateRequest.getProductName());
        if (optional.isPresent() && !(product.getProductCode().equals(optional.get().getProductCode()))) {
            throw new ProductAlreadyExistsException("Product with given Product Name already exists");
        }

        ProductUnitStrategy productUnitStrategy = PRODUCT_UNIT_STRATEGY_HASH_MAP.get(productUpdateRequest.getProductUnit());

        if (productUnitStrategy == null) {
            throw new InputMismatchException("Product Unit must be either PIECE or KILOGRAM");
        }

        String barcodeUrl = "http://localhost:8080/v1/api/barcode" + "?productCode=" + id;
        try {
            restTemplate.delete(barcodeUrl);
        } catch (HttpClientErrorException e) {
            return ProductResponseDto.builder()
                    .productDtoList(new ArrayList<>())
                    .message(e.getMessage())
                    .isSuccess(false)
                    .build();
        }

        product.setProductName(productUpdateRequest.getProductName());
        product.setBrand(productUpdateRequest.getBrand());
        product.setProductUnit(productUnitStrategy.getProductUnit());

        ProductDto updatedProduct = productMapper.toProductDto(productRepository.save(product));

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(updatedProduct);

        return ProductResponseDto.builder()
                .productDtoList(productDtoList)
                .message(HttpStatus.OK.toString())
                .isSuccess(true)
                .build();
    }

}
