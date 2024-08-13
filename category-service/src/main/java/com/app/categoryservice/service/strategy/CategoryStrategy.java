package com.app.categoryservice.service.strategy;

import com.app.categoryservice.model.entity.Category;

public interface CategoryStrategy {
    void register();
    Category getCategory();
}
