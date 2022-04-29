package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

import java.util.ArrayList;

public class HelloController {

    @FXML
    private TextField loginText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label errorLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws Exception {
        ArrayList<user> temp = serialization.deserializeUserList("data.txt");
        Long loginT = Long.valueOf(loginText.getText());
        String passT = passwordText.getText();
        for(int i = 0; i<temp.size();i++)
        {
            if(loginT.equals(temp.get(i).getLogin())&&passT.equals(temp.get(i).getPassword()))
            {
                loginText.setText("");
                passwordText.setText("");
                errorLabel.setText("");
                if(temp.get(i).isAdminAccess())
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("panelAdmin.fxml"));
                    root=loader.load();
                }
                else
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("panelUser.fxml"));
                    root=loader.load();
                    userController userController = loader.getController();
                    userController.initUser(temp.get(i));
                }

                switchToScene2(event);
                break;
            }
            else{
                errorLabel.setText("Niepoprawne dane!");
            }
        }
    }

    public void switchToScene2(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    

}