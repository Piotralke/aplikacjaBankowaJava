package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class adminTransferController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField amountText;
    @FXML
    private ChoiceBox currencyBox;
    @FXML
    private Button transferButton;

    public void init(){
        String[] valueArray = countryHashMap.getValues();
        for(int j=0; j<valueArray.length;j++) {
            currencyBox.getItems().add(valueArray[j]);
        }
        currencyBox.setValue("PLN");
        transferButton.setDisable(true);
        amountText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                transferButton.setDisable(!(Float.parseFloat(newValue) > 0.0f));
            }else
                transferButton.setDisable(true);
        });
    }

    @FXML
    protected void transfer() throws IOException, ClassNotFoundException {
        String login = serialization.deserializeString("login.txt");
        ArrayList<user> userList = serialization.deserializeUserList("data.txt");
        int j;
        for(j = 0;j<userList.size()-1;j++)
        {
            if(userList.get(j).getLogin().equals(Long.valueOf(login)))
            {
                break;
            }
        }
        ArrayList<transaction> transactions = userList.get(j).getTransacionList();
        Float amountT = currencyConverter.convertCurrency(Float.parseFloat(amountText.getText()), currencyBox.getValue().toString(), userList.get(j).getCurrency());
        transaction newT = new transaction(amountT,"Wpłata w placówce banku",0l,"Bank PDJD",userList.get(j).getAccNumber(),userList.get(j).getName());
        userList.get(j).setBalance(userList.get(j).getBalance()+amountT);
        transactions.add(0,newT);
        userList.get(j).setTransacionList(transactions);
        serialization.serializeUserList("data.txt",userList);
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
