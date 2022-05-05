package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class transferController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label ballanceLabel;
    @FXML
    private TextField nameText;
    @FXML
    private TextField numAccText;
    @FXML
    private TextField titleText;
    @FXML
    private TextField amountText;
    @FXML
    private Button transferButton;
    private boolean checkName;
    private boolean checkNumAcc;
    private boolean checkTitle;
    private boolean checkAmount;

    public void init() throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        Long loginT = Long.valueOf(serialization.deserializeString("login.txt"));
        for(int i=0;i<tempList.size();i++){
            if(loginT.equals(tempList.get(i).getLogin())) {
                ballanceLabel.setText("Twój stan konta:\n"+tempList.get(i).getBalance().toString()+tempList.get(i).getCurrency());
                break;
            }
        }
        transferButton.setDisable(true);
        numAccText.textProperty().addListener((observable, oldValue, newValue) -> {
            checkNumAcc= newValue.length() == 14;
            check();
        });
        nameText.textProperty().addListener((observable, oldValue, newValue) -> {
            checkName=!newValue.trim().isEmpty();
            check();
        });
        titleText.textProperty().addListener((observable, oldValue, newValue) -> {
            checkTitle=!newValue.trim().isEmpty();
            check();
        });
        amountText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                checkAmount= Float.parseFloat(newValue) > 0.0f;
            }else
                checkAmount=false;
            check();
        });
    }

    protected void check(){
        transferButton.setDisable(!checkName || !checkNumAcc || !checkAmount || !checkTitle);
    }
    @FXML
    protected void transfer() throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        Long loginT = Long.valueOf(serialization.deserializeString("login.txt"));
        int i;
        for(i=0;i<tempList.size();i++){
            if(loginT.equals(tempList.get(i).getLogin())) {
                break;
            }
        }
        tempList.get(i).setBalance(tempList.get(i).getBalance()-Float.parseFloat(amountText.getText()));
        transaction newT = new transaction(Float.valueOf(amountText.getText()),titleText.getText(),tempList.get(i).getAccNumber(),tempList.get(i).getName(),Long.valueOf(numAccText.getText()),nameText.getText(),tempList.get(i).getBalance(),true,tempList.get(i).getCurrency());
        ArrayList<transaction> tempTransList = tempList.get(i).getTransacionList();
        tempTransList.add(0,newT);
        tempList.get(i).setTransacionList(tempTransList);

        ballanceLabel.setText("Twój stan konta:\n"+tempList.get(i).getBalance().toString()+tempList.get(i).getCurrency());
        int j;
        for(j=0;j<tempList.size();j++){
            if(Long.valueOf(numAccText.getText()).equals(tempList.get(j).getAccNumber())){
                Float amountT = currencyConverter.convertCurrency(Float.parseFloat(amountText.getText()), tempList.get(i).getCurrency(), tempList.get(j).getCurrency());
                tempList.get(j).setBalance(tempList.get(j).getBalance()+amountT);
                tempTransList=tempList.get(j).getTransacionList();
                transaction newT2 = new transaction(amountT,titleText.getText(),tempList.get(i).getAccNumber(),tempList.get(i).getName(),Long.valueOf(numAccText.getText()),nameText.getText(),tempList.get(j).getBalance(),false,tempList.get(j).getCurrency());
                tempTransList.add(0,newT2);
                tempList.get(j).setTransacionList(tempTransList);
                break;
            }
        }
        serialization.serializeUserList("data.txt",tempList);
    }

    @FXML
    protected void goBack(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panelUser.fxml"));
        root=loader.load();
        userController userController = loader.getController();
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        Long loginT = Long.valueOf(serialization.deserializeString("login.txt"));
        int i;
        for(i=0;i<tempList.size();i++){
            if(loginT.equals(tempList.get(i).getLogin())) {
                break;
            }
        }
        userController.initUser(tempList.get(i),i);
        userController.initList(tempList.get(i));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
