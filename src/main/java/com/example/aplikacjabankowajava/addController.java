package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class addController {

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
    private CheckBox isadmin;
    @FXML
    private ChoiceBox countryChoice;
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void addUser() throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");

        user tempUser = new user(name.getText(), surname.getText(), Long.valueOf(login.getText()), password.getText(), isadmin.isSelected(), countryChoice.getSelectionModel().getSelectedItem().toString(), false);
        tempList.add(tempUser);
        serialization.serializeUserList("data.txt",tempList);
        tempList = new ArrayList<>();
    }

    public void init(boolean manager) throws IOException, ClassNotFoundException {
        if(manager)
            isadmin.setVisible(true);
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        Random rand = new Random();
        Long tempL = 10000000+rand.nextLong(89999999);
        for(int i=0;i< tempList.size();i++){
            if(tempL.equals(tempList.get(i).getLogin())){
                tempL = 10000000+rand.nextLong(89999999);
                i=0;
            }
        }
        tempList = new ArrayList<>();
        login.setText(tempL.toString());
        String passwordT = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        password.setText(passwordT);
        String[] countryArray = countryHashMap.getCountries();
        for(int i=0; i<countryArray.length;i++) {
            countryChoice.getItems().add(countryArray[i]);
        }
    }
}
