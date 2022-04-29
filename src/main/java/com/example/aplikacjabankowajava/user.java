package com.example.aplikacjabankowajava;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static com.example.aplikacjabankowajava.AESEncryption.decryptData;
import static com.example.aplikacjabankowajava.AESEncryption.encryptData;


public class user implements Serializable {
    //private static final long serialVersionUID = ;
    private String name;
    private String surname;
    private Long login;
    private transient String password;
    private boolean adminAccess;
    private Float balance;
    private Long accNumber;
    private String country;
    private String currency;
    private ArrayList<transaction> transacionList = new ArrayList<>();
    private boolean active;
    private boolean manager;

    public user() {
    }
    public user(String name, String surname, Long login, String password, boolean adminAccess, String country, boolean manager){
        this.name=name;
        this.surname=surname;
        this.login=login;
        this.password=password;
        this.adminAccess=adminAccess;
        this.balance = 0.0f;
        this.accNumber = generate(country,login);
        this.country = country;
        this.currency = countryHashMap.getCurrency(country);
        this.active = false;
        this.manager = manager;
    }
    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        String encryptPassword = encryptData(password);
        oos.writeObject(encryptPassword);
    }
    private void readObject(ObjectInputStream ois) throws Exception{
        ois.defaultReadObject();
        this.password = (Objects.requireNonNull(decryptData((String)ois.readObject())));
    }
    private Long generate(String country, Long login){
        //BANK NUMBER(8)- LOGIN(8)- ACC NUMBER(4) - COUNTRY(2)
        Random rand = new Random();
        int temp = 1000+rand.nextInt(8999);

        StringBuilder number = new StringBuilder().append(login).append(temp).append(countryHashMap.getCountryID(country));
        Long output = Long.valueOf(number.toString());
        return output;
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
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setLogin(Long login){
        this.login = login;
    }
    public Long getLogin(){
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
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
    public boolean isManager() {return manager;}
    public void setManager(boolean manager) {this.manager = manager;}

    public ArrayList<transaction> getTransacionList() {
        return transacionList;
    }
    public void setTransacionList(ArrayList<transaction> transacionList) {
        this.transacionList = transacionList;
    }
}