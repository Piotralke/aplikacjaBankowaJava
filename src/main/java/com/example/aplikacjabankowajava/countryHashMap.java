package com.example.aplikacjabankowajava;

import java.io.Serializable;
import java.util.HashMap;

public class countryHashMap implements Serializable {
    private static HashMap<String, String> countryCurrency = new HashMap<>();
    public static void init(){
        countryCurrency.put("Polska", "PLN");
        countryCurrency.put("Tajlandia", "THB");
        countryCurrency.put("Stany Zjednoczone", "USD");
        countryCurrency.put("Australia", "AUD");
        countryCurrency.put("Hongkong", "HKD");
        countryCurrency.put("Kanada", "CAD");
        countryCurrency.put("Nowa Zelandia", "NZD");
        countryCurrency.put("Singapur", "SGD");
        countryCurrency.put("Francja", "EUR");
        countryCurrency.put("Niemcy", "EUR");
        countryCurrency.put("Hiszpania", "EUR");
        countryCurrency.put("Włochy", "EUR");
        countryCurrency.put("Słowacja", "EUR");
        countryCurrency.put("Węgry", "HUF");
        countryCurrency.put("Szwajcaria", "CHF");
        countryCurrency.put("Wielka Brytania", "GBP");
        countryCurrency.put("Ukraina", "UAH");
        countryCurrency.put("Japonia", "JPY");
        countryCurrency.put("Czechy", "CZK");
        countryCurrency.put("Dania", "DKK");
        countryCurrency.put("Islandia", "ISK");
        countryCurrency.put("Norwegia", "NOK");
        countryCurrency.put("Szwecja", "SEK");
        countryCurrency.put("Chorwacja", "HRK");
        countryCurrency.put("Rumunia", "RON");
        countryCurrency.put("Bułgaria", "BGN");
        countryCurrency.put("Turcja", "TRY");
        countryCurrency.put("Izrael", "ILS");
        countryCurrency.put("Chile", "CLP");
        countryCurrency.put("Filipiny", "PHP");
        countryCurrency.put("Meksyk", "MXN");
        countryCurrency.put("Republika Południowej Afryki", "ZAR");
        countryCurrency.put("Brazylia", "BRL");
        countryCurrency.put("Malezja", "MYR");
        countryCurrency.put("Indonezja", "IDR");
        countryCurrency.put("Indie", "INR");
        countryCurrency.put("Korea Południowa", "KRW");
        countryCurrency.put("Chiny", "CNY");
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
    public static String[] getValues(){
        String[] array = new String[countryCurrency.size()];
        int j=0;
        for (String i : countryCurrency.values()) {
            array[j]=i;
            j++;
        }
        //array[0] = getCurrency("Poland");
        //for(String i : countryCurrency.values()){
        //    for(int k=0;k < array.length;k++){
        //        if(i.equals(array[k]))
        //            break;
        //        else
        //            array[k]=i;
        //    }
        //}
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
