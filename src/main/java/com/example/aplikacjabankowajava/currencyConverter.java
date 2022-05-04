package com.example.aplikacjabankowajava;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;
import javax.money.*;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class currencyConverter {
    @Test
    public static Float convertCurrency(Float inputBalance, String inputCurrency, String output)
    {
      //  MonetaryAmount money = Money.of(inputBalance,inputCurrency);
        MonetaryAmount money = Monetary.getDefaultAmountFactory().setCurrency(inputCurrency).setNumber(inputBalance).create();
        CurrencyConversion conversion = MonetaryConversions.getConversion(output);
      //  assertNotNull(conversion);
        MonetaryAmount convertedMoney = money.with(conversion);
        Float out = Float.valueOf(money.with(conversion).toString());
        StringBuilder temp = new StringBuilder().append(inputCurrency).append(" ").append(inputCurrency);
        assertEquals(temp,money.toString());
        assertNotNull(convertedMoney);
        System.out.println(convertedMoney);
        return out;
    }
}

