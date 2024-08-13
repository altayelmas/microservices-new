package com.app.categoryservice.service;

import com.app.categoryservice.model.dto.CategoryDto;

public interface CategoryService {
     CategoryDto getCategory(String categoryName);
}
