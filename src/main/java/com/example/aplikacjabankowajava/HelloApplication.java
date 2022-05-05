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
        //user admin = new user("admin","admin",12345678l,"admin",true,"Poland",true);
        //user admin2 = new user("admin2","admin2",12345679l,"admin2",true,"Germany",false);
        //user user1 = new user("Michal","Mlodawski",69696969l,"java",false,"United States",false);
        //user1.setBalance(2137.0f);
        //userArrayList.add(admin);
        //userArrayList.add(admin2);
        //userArrayList.add(user1);
        //serialization.serializeUserList("data.txt",userArrayList);
    }

    public static void main(String[] args) {
        launch();
    }

}