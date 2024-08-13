package com.app.productservice.controller;

import com.app.productservice.model.dto.ProductCreateRequest;
import com.app.productservice.model.dto.ProductDto;
import com.app.productservice.model.dto.ProductResponseDto;
import com.app.productservice.model.dto.ProductUpdateRequest;
import com.app.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductCreateRequest productCreateRequest) {
        ProductResponseDto productResponseDto = productService.createProduct(productCreateRequest);
        if (productResponseDto.isSuccess()) {
            return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(productResponseDto, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<ProductResponseDto> get(@RequestParam String id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ProductResponseDto> delete(@RequestParam String id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> update(@RequestParam String id, @RequestBody ProductUpdateRequest productUpdateRequest) {
        return new ResponseEntity<>(productService.updateProduct(id, productUpdateRequest), HttpStatus.OK);
    }
}
