package com.example.aplikacjabankowajava;

import java.io.*;
import java.util.ArrayList;

public class serialization {

    public static ArrayList<user> deserializeUserList(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<user>)objectInputStream.readObject();
    }
    public static void serializeUserList(String path, ArrayList<user> userArrayList) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(path);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(userArrayList);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public static ArrayList<transaction> deserializeTransactionList(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<transaction>)objectInputStream.readObject();
    }
    public static void serializeTransactionList(String path, ArrayList<transaction> transactions) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(path);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(transactions);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public static boolean deserializeManager(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (boolean)objectInputStream.readObject();
    }
    public static void serializeManager(String path, boolean manager) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(path);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(manager);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
