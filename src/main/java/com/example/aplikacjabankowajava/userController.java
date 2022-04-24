package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class userController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label balanceLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initUser(user user){
        welcomeLabel.setText("Witaj " + user.getName());
        balanceLabel.setText(user.getBalance().toString() + user.getCurrency());
    }

    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelLogowania.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
