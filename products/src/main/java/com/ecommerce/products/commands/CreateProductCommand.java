package com.ecommerce.products.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductCommand extends BaseCommand{
    private String name;
    private int quantity;
    private float price;
    private String description;


}
