package com.app.categoryservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDto {
    private String name;
    private String categoryCode;
}
