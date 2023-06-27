package com.ecommerce.id.generator.controller;

import com.ecommerce.id.generator.exceptions.ClockMovedBackException;
import com.ecommerce.id.generator.exceptions.OutOfBoundNodeIdException;
import com.ecommerce.id.generator.service.UniqueIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GenerateUniqueIdController {
    @Autowired
    private UniqueIdService uniqueIdService;

    @GetMapping("/id")
    public long getId() throws OutOfBoundNodeIdException, ClockMovedBackException {
        return uniqueIdService.generateId();
    }
}
