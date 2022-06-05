package com.thurpe.inventorymanagement;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		run(InventoryManagementApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			ProductDTO product1 = new ProductDTO("Toothpaste", 20, "This is a product for the future");
//			Category category1 = new Category("Stationeries");
//		};
//	}
}
