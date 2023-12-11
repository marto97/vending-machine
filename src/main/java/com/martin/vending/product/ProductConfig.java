package com.martin.vending.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
            Product pepsi = new Product(
                    "Pepsi",
                    "drinks",
                    "A1",
                    1.50
            );

            Product chio = new Product(
                    "Chio",
                    "snacks",
                    "A2",
                    1.80
            );

            Product milka = new Product(
                    "Milka",
                    "snacks",
                    "A3",
                    1.20
            );

            repository.saveAll(
                    List.of(pepsi, chio, milka)
            );
        };
    }
}
