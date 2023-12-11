package com.martin.vending.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private static final int MAX_PRODUCTS_TYPE = 10;

    private final ProductRepository productRepository;

    public Product selectedProduct;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByType(String type) {
        return productRepository.findProductsByType(type);
    }
    public Optional<Product>  getProductsByLocation(String location) {
        return productRepository.findProductsByLocation(location);
    }
    public void addNewProduct(Product product) {
        Optional<Product>  productOptional = productRepository
                .findProductsByLocation(product.getLocation());
        if(productOptional.isPresent()){
            throw new IllegalStateException("location taken");
        }

        List<Product> productListSameType = productRepository
                .findProductsByType(product.getType());
        if(productListSameType.size() >= MAX_PRODUCTS_TYPE){
            throw new IllegalStateException("The machine can hold up to 10 products of the same type.");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "product with id " + id + " does not exists");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Long id, String name, String type, String location, double cost) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "product with id " + id + "does not exist"
                ));

        if(name != null
                && name.length() > 0 &&
                !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }

        if(type != null
                && type.length() > 0 &&
                !Objects.equals(product.getType(), type)) {
            product.setType(type);
        }

        if(location != null
                && location.length() > 0 &&
                !Objects.equals(product.getLocation(), location)) {
            Optional<Product> productOptional = productRepository
                    .findProductsByLocation(location);

            if(productOptional.isPresent()){
                throw new IllegalStateException("location taken");
            }

            product.setLocation(location);
        }

        if(!Objects.equals(product.getCost(), cost)) {
            product.setCost(cost);
        }




    }

    public boolean isProductAvailable(String productLocation) {
        var productsByLocation = getProductsByLocation(productLocation);
        selectedProduct = productsByLocation.stream().findFirst().orElse(null);
        return productsByLocation.stream().findAny().isPresent();
    }

    public double getProductCost(String productLocation) {
        return selectedProduct != null
                ? selectedProduct.getCost()
                : getProductsByLocation(productLocation).stream()
                .findFirst().orElse(new Product())
                .getCost();
    }

    public boolean hasSufficientFunds(double productCost, BigDecimal funds) {
        return funds.compareTo(new BigDecimal(productCost)) >= 0;
    }
}
