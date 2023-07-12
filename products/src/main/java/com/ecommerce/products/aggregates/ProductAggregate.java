package com.ecommerce.products.aggregates;

import com.ecommerce.products.commands.CreateProductCommand;
import com.ecommerce.products.commands.UpdateProductCommand;
import com.ecommerce.products.events.ProductCreatedEvent;
import com.ecommerce.products.events.ProductUpdateEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductAggregate extends AggregateRoot{

    public ProductAggregate(CreateProductCommand command){
        raiseEvent(ProductCreatedEvent.builder()
                        .id(command.getId())
                        .name(command.getName())
                        .quantity(command.getQuantity())
                        .price(command.getPrice())
                        .description(command.getDescription())
                        .build());
    }

    public void apply(ProductCreatedEvent event){
        this.id = event.getId();
    }

    public void updateProduct(UpdateProductCommand command){
        raiseEvent(
                ProductUpdateEvent.builder()
                        .id(this.id)
                        .name(command.getName())
                        .description(command.getDescription())
                        .quantity(command.getQuantity())
                        .price(command.getPrice())
                        .build()
        );
    }

    public void apply(ProductUpdateEvent event){
        this.id = event.getId();
    }
}
