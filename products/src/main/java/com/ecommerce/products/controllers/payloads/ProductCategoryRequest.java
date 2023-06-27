package com.ecommerce.products.controllers.payloads;

import com.ecommerce.products.models.ProductCategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryRequest {
    private String name;
    private ProductCategoryType categoryType;
}
