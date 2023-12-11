package com.martin.vending.product;

import com.martin.vending.coin.Coin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestProduct {

    private List<Coin> insertedCoins;
    private String productLocation;

    public List<Coin> getInsertedCoins() {
        return this.insertedCoins;
    }

    public void setInsertedCoins(List<Coin> insertedCoins) {
        this.insertedCoins = insertedCoins;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

}
