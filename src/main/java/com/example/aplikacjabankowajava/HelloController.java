package com.example.aplikacjabankowajava;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.simtechdata.Switcher;

import java.io.*;

import com.example.aplikacjabankowajava.HelloApplication.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;

import static com.example.aplikacjabankowajava.AESEncryption.decryptData;


public class HelloController {
    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;
    @FXML
    private Label balanceLabel;

    @FXML
    protected void onHelloButtonClick() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("data.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        user temp = (user)objectInputStream.readObject();
        Long loginT = Long.valueOf(loginText.getText());
        String passT = passwordText.getText();
        errorLabel.setText(temp.getPassword() + temp.getLogin());
        if(loginT.equals(temp.getLogin())&&passT.equals(temp.getPassword()))
        {
            Switcher.showScene(2);
        }

    else{
            //errorLabel.setText("Login " + +);
        }
    }

}