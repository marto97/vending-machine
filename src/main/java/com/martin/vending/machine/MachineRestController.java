package com.martin.vending.machine;

import com.martin.vending.product.RequestProduct;
import com.martin.vending.product.VendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MachineRestController {

    private Machine machine;

    @Autowired
    public void Machine(Machine machine) {
        this.machine = machine;
    }


    @PostMapping("/purchaseProduct")
    public VendProduct purchaseProduct(@RequestBody RequestProduct requestProduct) {
        return machine.purchase(requestProduct.getProductLocation(), requestProduct.getInsertedCoins());
    }
}
