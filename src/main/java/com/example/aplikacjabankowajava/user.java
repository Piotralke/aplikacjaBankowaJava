package com.example.aplikacjabankowajava;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import com.example.aplikacjabankowajava.countryHashMap.*;

import static com.example.aplikacjabankowajava.AESEncryption.decryptData;
import static com.example.aplikacjabankowajava.AESEncryption.encryptData;


public class user implements Serializable {
    //private static final long serialVersionUID = ;
    private String name;
    private String surname;
    private transient Long login;
    private String password;
    private boolean adminAccess;
    private Float balance;
    private Long accNumber;
    private String country;
    private String currency;
    private countryHashMap countryMap = new countryHashMap();


    public user() {
    }
    public user(String name, String surname, Long login, String password, boolean adminAccess, String country){
        this.name=name;
        this.surname=surname;
        this.login=login;
        this.password=password;
        this.adminAccess=adminAccess;
        this.balance = 0.0f;
        this.accNumber = generate(country,login);
        this.country = country;
        this.currency = countryMap.getCurrency(country);
    }
    public void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        String encryptPassword = encryptData(login.toString());
        oos.writeObject(encryptPassword);
    }
    public void readObject(ObjectInputStream ois) throws Exception{
        ois.defaultReadObject();
        this.login = Long.valueOf(Objects.requireNonNull(decryptData((String)ois.readObject())));
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }
    public void setLogin(Long login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }
    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

}
