package com.martin.vending.machine;

import com.martin.vending.coin.Coin;
import com.martin.vending.coin.CoinService;
import com.martin.vending.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import com.martin.vending.product.VendProduct;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Machine {
    private final ProductService productService;
    private final CoinService coinService;

    @Autowired
    public Machine( ProductService productService, CoinService coinService) {
        this.productService = productService;
        this.coinService = coinService;
    }

    public VendProduct purchase(String productSelection, List<Coin> coins) {
        var validCoins = Optional.of(coins).stream().flatMap(Collection::stream)
                .filter(CoinService::isValidCoin)
                .collect(Collectors.toList());
        var funds = CoinService.countCoins(validCoins);
        var productCost = productService.getProductCost(productSelection);
        validatePurchase(productSelection, funds, productCost);

        return new VendProduct(productService.getProductsByLocation(productSelection).orElse(null), "Thank you for your purchase! " );
    }

    private void validatePurchase(String productSelection, BigDecimal funds, double productCost) {
        if (!productService.isProductAvailable(productSelection)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Unavailable");
        }
        if (!productService.hasSufficientFunds(productCost, funds)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds of " + funds);
        }
    }
}
