package com.ecommerce.products;

import com.ecommerce.products.commands.CreateProductCommand;
import com.ecommerce.products.commands.ProductCommandDispatcher;
import com.ecommerce.products.commands.ProductCommandHandler;
import com.ecommerce.products.commands.UpdateProductCommand;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductsApplication {

	@Autowired
	private ProductCommandDispatcher commandDispatcher;

	@Autowired
	private ProductCommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@PostConstruct
	public void registerHandler(){
		commandDispatcher.registerHandler(CreateProductCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(UpdateProductCommand.class, commandHandler::handle);
	}

}
