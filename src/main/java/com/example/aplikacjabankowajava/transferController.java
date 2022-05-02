package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void init() throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        Long loginT = Long.valueOf(serialization.deserializeString("login.txt"));
        for(int i=0;i<tempList.size();i++){
            if(loginT.equals(tempList.get(i).getLogin())) {
                ballanceLabel.setText("Twój stan konta:\n"+tempList.get(i).getBalance().toString()+tempList.get(i).getCurrency());
                break;
            }
        }

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
        transaction newT = new transaction(Float.valueOf(amountText.getText()),titleText.getText(),tempList.get(i).getAccNumber(),tempList.get(i).getName(),Long.valueOf(numAccText.getText()),nameText.getText());
        ArrayList<transaction> tempTransList = tempList.get(i).getTransacionList();
        tempTransList.add(0,newT);
        tempList.get(i).setTransacionList(tempTransList);
        tempList.get(i).setBalance(tempList.get(i).getBalance()-Float.parseFloat(amountText.getText()));
        ballanceLabel.setText("Twój stan konta:\n"+tempList.get(i).getBalance().toString()+tempList.get(i).getCurrency());
        //int j;
        //for(j=0;j<tempList.size();j++){
        //    if(Long.valueOf(numAccText.getText()).equals(tempList.get(i).getAccNumber())){
        //        tempTransList=tempList.get(j).getTransacionList();
        //        tempTransList.add(0,newT);
        //        tempList.get(j).setTransacionList(tempTransList);
        //        break;
        //    }
        //}
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
