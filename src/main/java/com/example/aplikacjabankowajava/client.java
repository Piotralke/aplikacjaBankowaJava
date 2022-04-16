package com.example.aplikacjabankowajava;

public class client extends user{
    Float balance;
    Long accNumber; //unique?
    // array obiektow typu transakcja jako historia transakcji
    public client(String name, String surname, String login, String password){

    }
    public Float getBalance(){
        return balance;
    }
    public void setBalance(Float balance){
        this.balance = balance;
    }
    public void addBalance(Float balance){
        this.balance += balance;
    }
}
