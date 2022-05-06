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
import java.time.LocalDate;
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
    private Button payB;
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
    private credit tempC;

    public void init(credit credit, boolean isAdmin) throws IOException, ClassNotFoundException {
        tempC=credit;
        if(isAdmin){
            acceptB.setDisable(false);
            acceptB.setVisible(true);
            declineB.setDisable(false);
            declineB.setVisible(true);
            ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
            Long loginT = Long.valueOf(serialization.deserializeString("login.txt"));
            int i;
            for(i=0;i<tempList.size();i++){
                if(loginT.equals(tempList.get(i).getLogin())) {
                    break;
                }
            }
            int counter = 0;
            for(int j=0;j<tempList.get(i).getCreditList().size();j++){
                if(tempList.get(i).getCreditList().get(j).getStatus().equals("Zakończony"))
                    counter++;
            }
            riskText.setText(creditAlgorithms.getIndicator(creditAlgorithms.getCreditworthiness(credit.getPeriod(),credit.getExpenses(),credit.getEarnings(),credit.getPeople(),counter),credit.getAge(),credit.getCreditSum()));
            riskText.setVisible(true);
        }
        if(credit.getStatus().equals("Zaakceptowany")){
            generateB.setVisible(true);
            generateB.setDisable(false);
            payB.setVisible(true);
            payB.setDisable(false);
            earningsText.setText("Następny dzień spłaty:\n" + credit.getNextPayment());
            expensesText.setText("Pozostała ilość rat:\n" + credit.getRemainingInstalments());
            peopleText.setText("Pozostała łączna kwota do spłaty:\n" +String.format("%.02f",credit.getCreditSum()) );
        }else if(credit.getStatus().equals("Zakończony")){
            generateB.setVisible(true);
            generateB.setDisable(false);
            payB.setVisible(false);
            payB.setDisable(true);
            peopleText.setText("Ilość osób:\n" + credit.getPeople());
            earningsText.setText("Średnie zarobki:\n" + String.format("%.02f",credit.getEarnings())+credit.getCurrency());
            expensesText.setText("Średnie wydatki\n" + String.format("%.02f",credit.getExpenses())+credit.getCurrency());
        }else{
            payB.setVisible(false);
            payB.setDisable(true);
            peopleText.setText("Ilość osób:\n" + credit.getPeople());
            earningsText.setText("Średnie zarobki:\n" + String.format("%.02f",credit.getEarnings())+credit.getCurrency());
            expensesText.setText("Średnie wydatki\n" + String.format("%.02f",credit.getExpenses())+credit.getCurrency());
        }
        statusText.setText("Status:\t" + credit.getStatus());
        creditNumberText.setText("Kredyt: " + credit.getNumber());
        nameText.setText("Imię i nazwisko, wiek:\n" + credit.getName() + " " + credit.getSurname() + "," + credit.getAge());
        amountText.setText("Kwota kredytu:\n" +String.format("%.02f",credit.getAmount()) +credit.getCurrency());
        instalmentText.setText("Wysokość raty:\n" + credit.getInstalment()+credit.getCurrency());
        dateText.setText("Data zawarcia:\n" + credit.getDate());
        purposeText.setText("Przeznaczenie:\n" + credit.getPurpose());
        interestText.setText("Oprocentowanie:\n6.9%");
        periodText.setText("Okres spłaty:\n" + credit.getPeriod());
    }

    @FXML
    protected  void generate(){

    }

    @FXML
    protected void accept(ActionEvent event) throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        int i;
        for(i=0;i<tempList.size();i++){
            if(tempList.get(i).getAccNumber().equals(tempC.getNumAcc()))
                break;
        }
        tempC.setStatus("Zaakceptowany");
        tempC.setNextPayment(LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue()+1, tempC.getDay()));
        tempList.get(i).getCreditList().set(0,tempC);
        tempList.get(i).setBalance(tempList.get(i).getBalance()+tempC.getAmount());
        transaction tempT = new transaction(tempC.getAmount(), tempC.getNumber(), null,"Bank PDJD",tempC.getNumAcc(),tempC.getName(),tempList.get(i).getBalance(),false, tempC.getCurrency());
        tempList.get(i).getTransacionList().add(0,tempT);
        serialization.serializeUserList("data.txt",tempList);
        goBack(event);
    }

    @FXML
    protected void decline(ActionEvent event) throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        int i;
        for(i=0;i<tempList.size();i++){
            if(tempList.get(i).getAccNumber().equals(tempC.getNumAcc()))
                break;
        }
        tempC.setStatus("Odrzucony");
        tempList.get(i).getCreditList().set(0,tempC);
        serialization.serializeUserList("data.txt",tempList);
        goBack(event);
    }
    @FXML
    protected void payInstalment() throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        int i;
        for(i=0;i<tempList.size();i++){
            if(tempList.get(i).getAccNumber().equals(tempC.getNumAcc()))
                break;
        }
        if(tempList.get(i).getBalance()>= tempC.getInstalment()){
            tempList.get(i).setBalance(tempList.get(i).getBalance()- tempC.getInstalment());
            transaction tempT = new transaction(tempC.getInstalment(),"Spłata raty",tempC.getNumAcc(), tempC.getName(), null,"Bank PDJD",tempList.get(i).getBalance(),true, tempC.getCurrency());
            tempList.get(i).getTransacionList().add(0,tempT);
            if(tempC.getNextPayment().getMonthValue()+1>12){
                tempC.setNextPayment(LocalDate.of(tempC.getNextPayment().getYear()+1,1, tempC.getDay()));
            }else
                tempC.setNextPayment(LocalDate.of(tempC.getNextPayment().getYear(),tempC.getNextPayment().getMonthValue()+1, tempC.getDay()));
            tempC.setRemainingInstalments(tempC.getRemainingInstalments()-1);
            tempC.setCreditSum(tempC.getCreditSum() - tempC.getInstalment());
            if(tempC.getRemainingInstalments()==0){
                tempC.setStatus("Zakończony");
            }
            tempList.get(i).getCreditList().set(0,tempC);
            serialization.serializeUserList("data.txt",tempList);
            init(tempC,false);
        }

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
