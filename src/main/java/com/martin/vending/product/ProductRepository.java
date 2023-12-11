package com.martin.vending.product;

import com.martin.vending.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findProductsByName(String name);

    @Query("SELECT p FROM Product p WHERE p.type = ?1")
    List<Product> findProductsByType(String type);

    @Query("SELECT p FROM Product p WHERE p.location = ?1")
    Optional<Product>  findProductsByLocation(String name);
}

