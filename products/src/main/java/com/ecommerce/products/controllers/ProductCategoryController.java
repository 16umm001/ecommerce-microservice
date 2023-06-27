package com.ecommerce.products.controllers;


import com.ecommerce.products.controllers.payloads.ProductCategoryRequest;
import com.ecommerce.products.dto.ProductCategoryResponse;
import com.ecommerce.products.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<ProductCategoryResponse> create(@RequestBody ProductCategoryRequest productCategoryRequest){
        return ResponseEntity.ok(categoryService.createProductCategory(productCategoryRequest));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryResponse>> list(){
        return ResponseEntity.ok(categoryService.listProductCategories());
    }

}
