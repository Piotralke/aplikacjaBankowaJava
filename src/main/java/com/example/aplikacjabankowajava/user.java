package com.example.aplikacjabankowajava;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static com.example.aplikacjabankowajava.AESEncryption.encryptData;

public class user implements Serializable {
    //private static final long serialVersionUID = ;
    private String name;
    private String surname;
    private String login;
    transient String password;
    private boolean adminAccess;
    public user() {
    }
    public user(String name, String surname, String login, String password){
        this.name=name;
        this.surname=surname;
        this.login=login;
        this.password=password; //?
        //this.adminAccess=adminAccess;
    }
    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        String encryptPassword = encryptData(password);
        oos.writeObject(encryptPassword);
    }
    private void readObject(ObjectInputStream ois) throws Exception{
        ois.defaultReadObject();
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }
    public void setLogin(){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    public void setPassword()
    {
        this.setPassword();
    }
    //get password no nie do konca chyba
}
