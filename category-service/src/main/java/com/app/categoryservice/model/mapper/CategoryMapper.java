package com.app.categoryservice.model.mapper;

import com.app.categoryservice.model.dto.CategoryDto;
import com.app.categoryservice.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .categoryCode(category.getCategoryCode().toString())
                .build();
    }

}
