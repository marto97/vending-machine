package com.martin.vending.inventory;

import com.martin.vending.coin.Coin;
import com.martin.vending.coin.CoinService;
import com.martin.vending.product.RequestProduct;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static com.martin.vending.coin.Coin.ST10;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductDatabaseTest {

    private CoinService coinService;
    private RequestProduct requestProduct;

    @Before("")
    public void Setup() {
        coinService = new CoinService(requestProduct);
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidWeight() {
        var invalidWeight = new Coin(ST10.diameter.doubleValue(), 4,4);
        var actual = CoinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }
}
