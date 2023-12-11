package com.martin.vending.coin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Coin {
    public BigDecimal value;
    public BigDecimal weight;
    public BigDecimal diameter;

    public Coin(double diameter, double weight, double value) {
        this.value = new BigDecimal(value);
        this.weight = new BigDecimal(weight);
        this.diameter = new BigDecimal(diameter);
    }

    public static Coin ST10 = new Coin(18.50, 3.0, 0.10);
    public static Coin ST20 = new Coin(20.50, 4.0, 0.20);
    public static Coin ST50 = new Coin(22.50, 5.0, 0.50);
    public static Coin LV1 = new Coin(24.50, 7.0, 1.00);
    public static Coin LV2 = new Coin(26.5, 9.0, 2.00);
    public static List<Coin> VALID_COINS = Arrays.asList(ST10, ST20, ST50, LV1, LV2);
}
