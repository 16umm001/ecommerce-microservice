package com.ecommerce.products.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductCreatedEvent extends BaseEvent{
    private String name;
    private int quantity;
    private double price;
    private String description;
}