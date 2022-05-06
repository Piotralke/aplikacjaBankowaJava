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
    }

    public static void main(String[] args) {
        launch();
    }

}