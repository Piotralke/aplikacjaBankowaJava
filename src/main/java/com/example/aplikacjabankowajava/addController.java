package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
    private DatePicker datePicker;
    @FXML
    private Button button;
    private boolean checkLogin;
    private boolean checkName;
    private boolean checkSurname;
    private boolean checkAge;
    private boolean checkPassword;


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
        user tempUser = new user(name.getText(), surname.getText(), Long.valueOf(login.getText()), password.getText(), isadmin.isSelected(), countryChoice.getSelectionModel().getSelectedItem().toString(), false, datePicker.getValue());
        tempList.add(tempUser);
        serialization.serializeUserList("data.txt",tempList);
        tempList = new ArrayList<>();
    }

    private void check(){
        button.setDisable(!checkName || !checkAge || !checkLogin || !checkSurname || !checkPassword);
    }
    public void init(boolean manager) throws IOException, ClassNotFoundException {
        checkLogin=true;
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        Random rand = new Random();
        Long tempL = 10000000+rand.nextLong(89999999);
        for(int i=0;i< tempList.size();i++){
            if(tempL.equals(tempList.get(i).getLogin())){
                tempL = 10000000+rand.nextLong(89999999);
                i=0;
            }
        }
        login.setText(tempL.toString());
        datePicker.getEditor().textProperty().addListener(observable ->{
            if(datePicker.getValue().isBefore(LocalDate.now().minus(18, ChronoUnit.YEARS)))
                checkAge=true;
            else if(datePicker.getValue().isBefore(LocalDate.now().minus(100, ChronoUnit.YEARS)))
                checkAge=false;
            else
                checkAge=false;
            check();
        });
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            checkLogin= newValue.length() == 8;
            check();
        });
        login.textProperty().addListener((observable, oldValue, newValue) -> {
            checkPassword= !newValue.trim().isEmpty();
            check();
        });
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            checkName=!newValue.trim().isEmpty();
            check();
        });
        surname.textProperty().addListener((observable, oldValue, newValue) -> {
            checkSurname=!newValue.trim().isEmpty();
            check();
        });

        button.setDisable(true);
        if(manager)
            isadmin.setVisible(true);
        datePicker.setValue(LocalDate.now());

        String passwordT = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        password.setText(passwordT);
        String[] countryArray = countryHashMap.getCountries();
        for(int i=0; i<countryArray.length;i++) {
            countryChoice.getItems().add(countryArray[i]);
        }
        countryChoice.setValue("Polska");
    }
}
