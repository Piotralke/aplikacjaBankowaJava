package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class creditController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox periodChoice;
    @FXML
    private ChoiceBox dayChoice;
    @FXML
    private TextField purposeText;
    @FXML
    private TextField amountText;
    @FXML
    private TextField periodText;
    @FXML
    private TextField earningsText;
    @FXML
    private TextField peopleText;
    @FXML
    private TextField expensesText;
    @FXML
    private Button button;
    private boolean checkPurpose;
    private boolean checkAmount;
    private boolean checkPeriod;
    private boolean checkEarnings;
    private boolean checkPeople;
    private boolean checkExpenses;

    public void init(){
        button.setDisable(true);
        periodChoice.getItems().add("LAT");
        periodChoice.getItems().add("MSC");

        for(int i=1;i<29;i++){
            dayChoice.getItems().add(i);
        }
        periodChoice.setValue("MSC");
        dayChoice.setValue(1);

        purposeText.textProperty().addListener((observable, oldValue, newValue) -> {
            checkPurpose = !newValue.trim().isEmpty();
            check();
        });
        amountText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                checkAmount=Float.valueOf(newValue)>0.0f;
            } else
                checkAmount=false;
            check();
        });
        periodText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                checkPeriod = Integer.parseInt(newValue)>0&&Integer.parseInt(newValue)<300;
            }else
                checkPeriod=false;
            check();
        });
        earningsText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                checkEarnings = Float.valueOf(newValue)>0.0f;
            } else
                checkEarnings=false;
            check();
        });
        peopleText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                checkPeople=Integer.parseInt(newValue)>0;
            } else
                checkPeople=false;
            check();
        });
        expensesText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.trim().isEmpty()){
                checkExpenses=Float.valueOf(newValue)>0.0f;
            }else
                checkExpenses=false;
            check();
        });
    }

    protected  void check(){
        button.setDisable(!checkPurpose || !checkAmount || !checkPeriod || !checkEarnings || !checkPeople || !checkExpenses);
    }
    @FXML
    protected  void addCredit(ActionEvent event) throws IOException, ClassNotFoundException {
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
        int period = Integer.parseInt(periodText.getText());
        if(periodChoice.getValue().equals("LAT"))
            period = Integer.parseInt(periodText.getText())*12;
        credit newC = new credit(purposeText.getText(),Float.valueOf(amountText.getText()),period,Integer.parseInt(dayChoice.getValue().toString()),Float.valueOf(earningsText.getText()),Integer.parseInt(peopleText.getText()),Float.valueOf(expensesText.getText()),userList.get(j).getAge(),userList.get(j).getAccNumber(),userList.get(j).getCurrency(),userList.get(j).getName(),userList.get(j).getSurname());
        ArrayList<credit> tempL = userList.get(j).getCreditList();
        tempL.add(0,newC);
        userList.get(j).setCreditList(tempL);
        serialization.serializeUserList("data.txt",userList);
        goBack(event);
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
