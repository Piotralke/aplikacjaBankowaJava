package com.example.aplikacjabankowajava;

import java.io.Serializable;
import java.util.HashMap;
import javax.money.*;

public class countryHashMap implements Serializable {
    private static HashMap<String, String> countryCurrency = new HashMap<>();

    countryHashMap(){
        countryCurrency.put("Poland","PLN");
        countryCurrency.put("Germany", "EUR");
        countryCurrency.put("England", "GBP");
        countryCurrency.put("USA","USD");
    }
    public static String getCurrency(String country)
    {
        return countryCurrency.get(country);
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
