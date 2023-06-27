package com.ecommerce.products.dto;

import com.ecommerce.products.models.ProductCategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponse {
    private long id;
    private String name;
    private ProductCategoryType categoryType;
}
