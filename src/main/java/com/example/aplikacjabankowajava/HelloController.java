package com.example.aplikacjabankowajava;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.simtechdata.Switcher;

import java.io.IOException;
import com.example.aplikacjabankowajava.HelloApplication.*;


public class HelloController {
    @FXML
    private Label password;

    @FXML
    protected void onHelloButtonClick() {
        Switcher.showScene(2);
    }

}