package com.ecommerce.products.externalService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ID-GENERATOR-SERVICE/api/id")
public interface GenerateUniqueId {

    @GetMapping
    long getId();
}
