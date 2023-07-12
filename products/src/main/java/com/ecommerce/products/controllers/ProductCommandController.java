package com.ecommerce.products.controllers;


import com.ecommerce.products.commands.CreateProductCommand;
import com.ecommerce.products.commands.ProductCommandDispatcher;
import com.ecommerce.products.commands.ProductCommandHandler;
import com.ecommerce.products.commands.UpdateProductCommand;
import com.ecommerce.products.dto.MessageResponse;
import com.ecommerce.products.utils.GenerateUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductCommandController {
    @Autowired
    private ProductCommandHandler productCommandHandler;

    @Autowired
    private ProductCommandDispatcher commandDispatcher;

    @Autowired
    private GenerateUID generateUID;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse create(@RequestBody CreateProductCommand command){
        command.setId(generateUID.getId());
        commandDispatcher.send(command);
        return new MessageResponse("Product Created Successfully!");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MessageResponse update(@PathVariable long id, @RequestBody UpdateProductCommand command){
        command.setId(id);
        commandDispatcher.send(command);
        return new MessageResponse("Product Updated Successfully!");
    }
}
