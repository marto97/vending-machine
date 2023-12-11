package com.martin.vending.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.martin.vending.product.RequestProduct;

import static com.martin.vending.coin.Coin.VALID_COINS;

@Component
public class CoinService {

    private  final RequestProduct requestProduct;

    @Autowired
    public CoinService(RequestProduct requestProduct) {
        this.requestProduct = requestProduct;
    }

    public boolean isValidCoin(Coin coinToValidate) {
        System.out.println("MARTIN TEST333");
        System.out.println(coinToValidate.value);
        System.out.println(coinToValidate.weight);
        System.out.println(coinToValidate.diameter);
        System.out.println(VALID_COINS.stream().anyMatch(x -> hasValidDimensions(x, coinToValidate)));
        return VALID_COINS.stream().anyMatch(x -> hasValidDimensions(x, coinToValidate));
    }

    public String insertCoin(Coin coin) {

        List<Coin> currentCoinList = requestProduct.getInsertedCoins();
        currentCoinList.add(coin);
        requestProduct.setInsertedCoins(currentCoinList);

        return "Inserted coin";
    }

    public String reset() {
        List<Coin> coinList = requestProduct.getInsertedCoins();
        coinList.clear();
        requestProduct.setInsertedCoins(coinList);
        return "returned inserted coins";
    }

    public static BigDecimal countCoins(List<Coin> coins) {
        return Optional.ofNullable(coins).stream().flatMap(Collection::stream)
                .map(x -> x.value)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static boolean hasValidDimensions(Coin validCoin, Coin coinToValidate) {
        System.out.println("MARTIN TEST4444");
        System.out.println(coinToValidate.value);
        System.out.println(coinToValidate.weight.setScale(2, RoundingMode.HALF_UP));
        System.out.println(coinToValidate.diameter.setScale(2, RoundingMode.HALF_UP));
        System.out.println(validCoin.weight.setScale(2, RoundingMode.HALF_UP));
        System.out.println(validCoin.diameter.setScale(2, RoundingMode.HALF_UP));
        return (validCoin.diameter).setScale(2, RoundingMode.HALF_UP).equals((coinToValidate.diameter).setScale(2, RoundingMode.HALF_UP))
                && ((validCoin.weight).setScale(2, RoundingMode.HALF_UP)).equals((coinToValidate.weight).setScale(2, RoundingMode.HALF_UP));
    }
}
