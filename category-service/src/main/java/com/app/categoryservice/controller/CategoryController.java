package com.app.categoryservice.controller;

import com.app.categoryservice.model.dto.CategoryDto;
import com.app.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryDto> get(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.getCategory(name));
    }
}
