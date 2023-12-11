package com.martin.vending.product;

import jakarta.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long id;
    private String name;
    private String type;
    private String location;
    private double cost;

    public Product() {
    }

    public Product(Long id,
                   String name,
                   String type,
                   String location,
                   double cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.cost = cost;
    }

    public Product(String name,
                   String type,
                   String location,
                   double cost) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
