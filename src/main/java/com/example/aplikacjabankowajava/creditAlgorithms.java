package com.example.aplikacjabankowajava;

import org.testng.internal.collections.Pair;

public class creditAlgorithms {
    private static Float factorial(int period, Float instalment)
    {
        Float tempI = 0.0f;
        for(int i = period;i>0;i--)
        {
            tempI += instalment*i;
        }
        return tempI;
    }
    public static Pair<Float,Float> getCreditworthiness(int period,Float expenses,Float earnings,int people, int creditAmount)
    {
        Float tempExpenses = people*700.0f;
        Float maxInstalment;
        Float creditworthiness;
        if(expenses>=tempExpenses)
        {
           maxInstalment = earnings-expenses;
        }
        else
        {
            maxInstalment = earnings - tempExpenses;
        }
        Float maxCredit = maxInstalment * period;
        creditworthiness =maxCredit -  factorial(period,((0.006f-(0.00005f*creditAmount))*maxInstalment));
        return new Pair<>(maxInstalment,creditworthiness);
    }
    public static String getIndicator(Pair<Float,Float> creditworthiness,int age,Float creditBalance){
        double indicator = 0.0f;
        double indC = 0.0f;
        Float indA = Math.abs(age-35)*0.08f;
        if(creditworthiness.second()>creditBalance)
        {
            if(creditBalance/creditworthiness.second()<=0.60)
                indC=12.0f;
            else
                indC = (creditBalance/creditworthiness.second())*20.0f;   //20
        }else{
            if(creditworthiness.second()/creditBalance>=0.80)
                indC=24.0f;
            else
                indC = (Math.abs(Math.log10(creditworthiness.second()/creditBalance)))*250.0f;
        }
        indicator = indA+indC;
        System.out.println(indicator);
        if(indicator<18){
            return "Małe ryzyko";
        }else if(indicator<28){
            return "Średnie ryzyko";
        }else{
            return "Duże ryzyko";
        }
    }
}
