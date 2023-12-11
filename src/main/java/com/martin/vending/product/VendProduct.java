package com.martin.vending.product;

import java.util.Optional;

public class VendProduct {

    private Product product;
    private String message;

    public VendProduct(Product product, String message ) {
        this.product = product;
        this.message = message;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
