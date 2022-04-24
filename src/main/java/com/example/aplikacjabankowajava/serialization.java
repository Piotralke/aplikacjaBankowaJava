package com.example.aplikacjabankowajava;

import java.io.*;
import java.util.ArrayList;

public class serialization {

    public static ArrayList<user> deserializeList(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<user>)objectInputStream.readObject();
    }
    public static void serializeList(String path, ArrayList<user> userArrayList) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(path);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(userArrayList);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
