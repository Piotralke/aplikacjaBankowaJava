package com.example.aplikacjabankowajava;

import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;
import org.javamoney.moneta.Money;
import javax.money.*;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import static org.testng.Assert.assertNotNull;

public class currencyConverter {
  //  @Test
    public static Float convertCurrency(Float inputBalance, String inputCurrency, String output)
    {
        MonetaryAmount money = Money.of(inputBalance,inputCurrency);
        //MonetaryAmount money = Monetary.getDefaultAmountFactory().setNumber(inputBalance).create();
        CurrencyConversion conversion = MonetaryConversions.getConversion(inputCurrency,output);
      //  assertNotNull(conversion);
        Float out = Float.valueOf(money.with(conversion).toString()) ;
        return out;
    }
}

