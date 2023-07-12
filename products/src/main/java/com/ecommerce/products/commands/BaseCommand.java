package com.ecommerce.products.commands;

import com.ecommerce.products.dto.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseCommand extends Message {
    public BaseCommand(long id){
        super(id);
    }
}
