package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.aplikacjabankowajava.countryHashMap.getCurrency;
import static com.example.aplikacjabankowajava.currencyConverter.convertCurrency;

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
    private ChoiceBox countryChoice;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox isadmin;
    private int i;
    private String currency;
    private Float balance;
    public void init(String loginT) throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");

        for(i=0;i< tempList.size();i++){
            if(loginT.equals(tempList.get(i).getLogin().toString())){
                login.setText(tempList.get(i).getLogin().toString());
                password.setText(tempList.get(i).getPassword());
                name.setText(tempList.get(i).getName());
                surname.setText(tempList.get(i).getSurname());
                currency = tempList.get(i).getCurrency();
                balance = tempList.get(i).getBalance();
                if(serialization.deserializeManager("manager.txt")){
                    isadmin.setVisible(true);
                    isadmin.setSelected(tempList.get(i).isAdminAccess());
                }
                String[] countryArray = countryHashMap.getCountries();
                for(int j=0; j<countryArray.length;j++) {
                    countryChoice.getItems().add(countryArray[j]);
                }
                countryChoice.setValue(tempList.get(i).getCountry());
                datePicker.setValue(tempList.get(i).getBirthday());
                //countryChoice.setDisable(true);
                break;
            }
        }
    }

    @FXML
    protected void changeUser() throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        tempList.get(i).setLogin(Long.valueOf(login.getText()));
        tempList.get(i).setPassword(password.getText());
        tempList.get(i).setName(name.getText());
        tempList.get(i).setSurname(surname.getText());
        tempList.get(i).setCountry(countryChoice.getValue().toString());
        tempList.get(i).setCurrency(getCurrency(countryChoice.getValue().toString()));
        tempList.get(i).setBalance(convertCurrency(balance,currency,getCurrency(countryChoice.getValue().toString())));
        //ustawianie stanu konta po przeliczeniu waluty
        if(serialization.deserializeManager("manager.txt")){
            tempList.get(i).setAdminAccess(isadmin.isSelected());
        }
        serialization.serializeUserList("data.txt",tempList);
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
