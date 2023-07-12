package com.ecommerce.products.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ID-GENERATOR-SERVICE")
public interface GenerateUID {

    @GetMapping("/api/id")
    long getId();
}
