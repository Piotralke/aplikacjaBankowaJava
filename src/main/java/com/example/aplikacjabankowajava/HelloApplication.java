package com.example.aplikacjabankowajava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.simtechdata.Switcher;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("panelLogowania.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Aplikacja Bankowa!");
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        primaryStage.show();
        Switcher.addScene(1,scene,primaryStage);
        Switcher.showScene(1);
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("panelUser.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 600, 400);
        Switcher.addScene(2,scene2,primaryStage);

    }

    public static void main(String[] args) {
        launch();
    }
}