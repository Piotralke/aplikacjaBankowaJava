package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class changeController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField country;
    @FXML
    private CheckBox isadmin;
    public void init(String loginT) throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        int i;
        for(i=0;i< tempList.size();i++){
            if(loginT.equals(tempList.get(i).getLogin().toString())){
                login.setText(tempList.get(i).getLogin().toString());
                password.setText(tempList.get(i).getPassword());
                name.setText(tempList.get(i).getName());
                surname.setText(tempList.get(i).getSurname());
                country.setText(tempList.get(i).getCountry());
                break;
            }
        }

        //tempList = new ArrayList<>();
    }

    @FXML
    protected void changeUser(){

    }
    @FXML
    protected void goBack(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userList.fxml"));
        root=loader.load();
        listController listController = loader.getController();
        listController.initList();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
