package com.martin.vending.product;

import com.martin.vending.product.Product;
import com.martin.vending.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/vending")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/byType={productType}")
    public List<Product> getProductsByType(@PathVariable String productType) {
        return productService.getProductsByType(productType);
    }

    @GetMapping(path = "/byLocation={productLocation}")
    public Optional<Product> getProductsByLocation(@PathVariable String productLocation) {
        return productService.getProductsByLocation(productLocation);
    }


    @PostMapping
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }


    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{id}")
    public void updateProduct(
            @ PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) double cost
    ){
        productService.updateProduct(id, name, type, location, cost);
    }

}
