package com.bitirme.productservice.request;

import com.bitirme.productservice.dto.CategoryDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    private final String name;
    private final String description;

    public CategoryDto toDto(){
        return CategoryDto.builder()
                .name(name)
                .description(description)
                .build();
    }
}
