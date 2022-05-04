package com.example.aplikacjabankowajava;

import models.rates.ExchangeRatesSeries;
import models.tables.ArrayOfExchangeRatesTable;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Currency;
import http.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class currencyConverter {
    @Test
    public static Float convertCurrency(Float balance, String inputCurrency, String outputCurrency) throws IOException {
        ArrayOfExchangeRatesTable arrayERT = new TableA().currentTable();
        if(inputCurrency.equals("PLN"))
        {
            if(outputCurrency.equals("PLN"))
            {
                return balance;
            }
            for(int i=0;i<arrayERT.getExchangeRatesTables().size();i++)
            {
                for(int j=0;j<arrayERT.getExchangeRatesTables().get(i).getRates().size();j++)
                {
                    System.out.println(arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode());
                    if(arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode().equals(outputCurrency))
                    {
                        System.out.println(arrayERT.getExchangeRatesTables().get(i).getRates().get(i).getMid());
                        return balance / Float.valueOf((float) arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getMid());
                    }
                }

            }
        }
        else
        {
            for(int i=0;i<arrayERT.getExchangeRatesTables().size();i++)
            {
                for(int j=0;j<arrayERT.getExchangeRatesTables().get(i).getRates().size();j++)
                {
                    System.out.println(arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode());
                    if(arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode().equals(inputCurrency))
                    {
                        System.out.println(arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getMid());
                        Float result = balance * Float.valueOf((float) arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getMid());
                        if(outputCurrency.equals("PLN"))
                        {
                            return result;
                        }
                        else
                        {
                            convertCurrency(result,"PLN",outputCurrency);
                        }

                    }
                }

            }
        }
        return balance;
    }
}

