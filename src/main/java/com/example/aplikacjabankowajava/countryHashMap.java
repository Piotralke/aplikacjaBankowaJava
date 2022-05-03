package com.example.aplikacjabankowajava;

import java.io.Serializable;
import java.util.HashMap;
import javax.money.*;

public class countryHashMap implements Serializable {
    private static HashMap<String, String> countryCurrency = new HashMap<>();
    public static void init(){
        countryCurrency.put("Australia", "AUD");
        countryCurrency.put("Austria", "EUR");
        countryCurrency.put("Belgium", "EUR");
        countryCurrency.put("Brazil", "BRL");
        countryCurrency.put("Bulgaria", "BGN");
        countryCurrency.put("Canada", "CAD");
        countryCurrency.put("China","CNY");
        countryCurrency.put("Croatia","HRK");
        countryCurrency.put("Czech Republic", "CZK");
        countryCurrency.put("Denmark", "DKK");
        countryCurrency.put("Finland","EUR");
        countryCurrency.put("France", "EUR");
        countryCurrency.put("Germany", "EUR");
        countryCurrency.put("Hungary","HUF");
        countryCurrency.put("Iceland","ISK");
        countryCurrency.put("India","INR");
        countryCurrency.put("Israel","ILS");
        countryCurrency.put("Jamaica","JMD");
        countryCurrency.put("Japan","JPY");
        countryCurrency.put("Mexico","MXN");
        countryCurrency.put("Nigeria","NGN");
        countryCurrency.put("Philippines","PHP");
        countryCurrency.put("Poland","PLN");
        countryCurrency.put("Russia","RUB");
        countryCurrency.put("Saudi Arabia","SAR");
        countryCurrency.put("Senegal","XOF");
        countryCurrency.put("Serbia","RSD");
        countryCurrency.put("South Africa","ZAR");
        countryCurrency.put("Spain","EUR");
        countryCurrency.put("Turkey","TRY");
        countryCurrency.put("Ukraine","UAH");
        countryCurrency.put("United Kingdom", "GBP");
        countryCurrency.put("United States","USD");
    }
    public static String getCurrency(String country)
    {
        return countryCurrency.get(country);
    }
    public static String[] getCountries(){
        String[] array = new String[countryCurrency.size()];
        int j=0;
        for (String i : countryCurrency.keySet()) {
            array[j]=i;
            j++;
        }
        return array;
    }
    public static int getCountryID(String country)
    {
        int j = 0;
        for (String i : countryCurrency.keySet()) {
            if(i.equals(country)){
                return j+10;
            }
            j++;
        }
        return 0;
    }

}
