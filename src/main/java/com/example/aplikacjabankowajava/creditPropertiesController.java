package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class creditPropertiesController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button generateB;
    @FXML
    private Button acceptB;
    @FXML
    private Button declineB;
    @FXML
    private Label creditNumberText;
    @FXML
    private Label nameText;
    @FXML
    private Label amountText;
    @FXML
    private Label instalmentText;
    @FXML
    private Label dateText;
    @FXML
    private Label purposeText;
    @FXML
    private Label interestText;
    @FXML
    private Label periodText;
    @FXML
    private Label peopleText;
    @FXML
    private Label earningsText;
    @FXML
    private Label expensesText;
    @FXML
    private Label statusText;
    @FXML
    private Label riskText;


    public void init(credit credit, boolean isAdmin){
        if(isAdmin){
            acceptB.setDisable(false);
            acceptB.setVisible(true);
            declineB.setDisable(false);
            declineB.setVisible(true);
            riskText.setText("chuj");
            riskText.setVisible(true);
        }
        if(credit.getStatus().equals("Aktywny")){
            generateB.setVisible(true);
            generateB.setDisable(false);
        }
        statusText.setText("Status:\t" + credit.getStatus());
        creditNumberText.setText("Kredyt: " + credit.getNumber());
        nameText.setText("Imię i nazwisko, wiek:\n" + credit.getName() + " " + credit.getSurname() + "," + credit.getAge());
        amountText.setText("Kwota kredytu:\n" + credit.getAmount()+credit.getCurrency());
        instalmentText.setText("Wysokość raty:\n" + credit.getInstalment()+credit.getCurrency());
        dateText.setText("Data zawarcia:\n" + credit.getDate());
        purposeText.setText("Przeznaczenie:\n" + credit.getPurpose());
        interestText.setText("Oprocentowanie:\n6.9%");
        periodText.setText("Okres spłaty:\n" + credit.getPeriod());
        peopleText.setText("Ilość osób:\n" + credit.getPeople());
        earningsText.setText("Średnie zarobki:\n" + credit.getEarnings()+credit.getCurrency());
        expensesText.setText("Średnie wydatki\n" + credit.getExpenses()+credit.getCurrency());

    }

    @FXML
    protected  void generate(){

    }

    @FXML
    protected void accept(){

    }

    @FXML
    protected void decline(){

    }

    @FXML
    protected void goBack(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader;
        if(serialization.deserializeString("exit.txt").equals("user")){
            loader = new FXMLLoader(getClass().getResource("panelUser.fxml"));
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
        }else{
            loader = new FXMLLoader(getClass().getResource("userList.fxml"));
            root=loader.load();
            listController listController = loader.getController();
            listController.initList();
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
