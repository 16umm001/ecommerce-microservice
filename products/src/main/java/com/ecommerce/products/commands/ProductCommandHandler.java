package com.ecommerce.products.commands;


import com.ecommerce.products.aggregates.ProductAggregate;
import com.ecommerce.products.events.EventSourcingHandler;
import com.ecommerce.products.repositories.ProductRepository;
import com.ecommerce.products.utils.GenerateUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandHandler {
    private final ProductRepository productRepository;

    @Autowired
    private GenerateUID generateUID;

    @Autowired
    private EventSourcingHandler eventSourcingHandler;

    public ProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void handle(CreateProductCommand command){
        var aggregate = new ProductAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    public void handle(UpdateProductCommand command){
        var aggregate = eventSourcingHandler.getById(command.getId());
        eventSourcingHandler.save(aggregate);
    }

}
