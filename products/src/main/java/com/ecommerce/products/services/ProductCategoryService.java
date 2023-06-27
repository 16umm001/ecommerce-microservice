package com.ecommerce.products.services;


import com.ecommerce.products.controllers.payloads.ProductCategoryRequest;
import com.ecommerce.products.dto.ProductCategoryResponse;
import com.ecommerce.products.externalService.GenerateUniqueId;
import com.ecommerce.products.models.ProductCategory;
import com.ecommerce.products.repositories.ProductCategoryRepository;
import com.ecommerce.products.utils.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private GenerateUniqueId generateUniqueId;

    public ProductCategoryResponse createProductCategory(ProductCategoryRequest categoryRequest){
        long id = generateUniqueId.getId();
        ProductCategory productCategory = new ProductCategory(
                id,
                categoryRequest.getName(),
                categoryRequest.getCategoryType()
        );
        productCategoryRepository.save(productCategory);
        return new ProductCategoryResponse(id, productCategory.getName(), productCategory.getCategoryType());
    }

    public List<ProductCategoryResponse> listProductCategories(){
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        return ProductCategoryMapper.INSTANCE.mapProductCategory(productCategories);
    }
}
