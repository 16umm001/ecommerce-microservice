package com.ecommerce.products.utils;


import com.ecommerce.products.dto.ProductCategoryResponse;
import com.ecommerce.products.models.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    @Mapping(ignore = true, target = "subCategorySet")
    List<ProductCategoryResponse> mapProductCategory(List<ProductCategory> address);
}
