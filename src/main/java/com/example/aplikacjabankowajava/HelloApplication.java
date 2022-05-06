package com.example.aplikacjabankowajava;

import http.TableA;
import http.TableB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.tables.ArrayOfExchangeRatesTable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;



public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("panelLogowania.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Aplikacja Bankowa!");
        stage.setHeight(450);
        stage.setWidth(650);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        countryHashMap.init();
        //ArrayList<user> userArrayList = new ArrayList<>();
        //user admin = new user("Jan","Dyrduł",12345678l,"admin",true,"Polska",true,LocalDate.parse("2000-11-04") );
        //user admin2 = new user("Piotr","Dziewięcki",87654321l,"admin2",true,"Polska",true,LocalDate.parse("2002-01-22"));
        //user user1 = new user("Michal","Mlodawski",13372115l,"java",false,"Stany Zjednoczone",false,LocalDate.parse("2000-12-10"));
        //user user2 = new user("Piotr","Nowak",98765432l,"java",false,"Polska",false,LocalDate.parse("2000-12-10"));
        //user1.setBalance(2137.0f);
        //userArrayList.add(admin);
        //userArrayList.add(admin2);
        //userArrayList.add(user1);
        //userArrayList.add(user2);
        //serialization.serializeUserList("data.txt",userArrayList);
    }

    public static void main(String[] args) {
        launch();
    }

}