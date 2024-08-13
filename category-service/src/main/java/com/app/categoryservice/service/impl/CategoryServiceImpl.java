package com.app.categoryservice.service.impl;

import com.app.categoryservice.exception.CategoryNotFoundException;
import com.app.categoryservice.model.dto.CategoryDto;
import com.app.categoryservice.model.mapper.CategoryMapper;
import com.app.categoryservice.service.CategoryService;
import com.app.categoryservice.service.strategy.CategoryStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private static final HashMap<String, CategoryStrategy> CATEGORY_MAP = new HashMap<>();

    public static void addCategory(String categoryName, CategoryStrategy categoryStrategy) {
        CATEGORY_MAP.put(categoryName, categoryStrategy);
    }

    @Override
    public CategoryDto getCategory(String categoryName) {
        CategoryStrategy categoryStrategy = CATEGORY_MAP.get(categoryName);

        if (categoryStrategy == null) {
            throw new CategoryNotFoundException("Category not found");
        }

        return categoryMapper.toCategoryDto(categoryStrategy.getCategory());
    }

}
