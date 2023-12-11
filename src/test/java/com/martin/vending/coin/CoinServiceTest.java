package com.martin.vending.coin;

import com.martin.vending.product.RequestProduct;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.martin.vending.coin.Coin.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CoinServiceTest {

    private CoinService coinService;
    private RequestProduct requestProduct;

    @Before("")
    public void Setup() {

        coinService = new CoinService(requestProduct);
        requestProduct = new RequestProduct();
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidWeight() {
        var invalidWeight = new Coin(ST10.diameter.doubleValue(), 4,4);
        var actual = CoinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void countCoins() {

        var ST10 = Coin.ST10;
        var ST20 = Coin.ST20;
        List<Coin> coinList = List.of(ST10,ST20);
        var result = CoinService.countCoins(coinList);

        assertEquals(new BigDecimal("0.30"), result.setScale(2, RoundingMode.HALF_UP));
    }

}
