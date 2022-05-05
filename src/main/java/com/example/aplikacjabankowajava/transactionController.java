package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class transactionController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView listView;

    @FXML
    public void initList() throws IOException, ClassNotFoundException {
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
        for(int i=0;i<transactions.size();i++)
        {
            if(transactions.get(i).isTransactionType()){
                listView.getItems().add("-" + String.format("%.02f", transactions.get(i).getBalance()) + transactions.get(i).getCurrency() + "\t\t" + transactions.get(i).getTitle() +"\t\t" + transactions.get(i).getSecondAccName());
            }
            else{
                listView.getItems().add("+" + String.format("%.02f", transactions.get(i).getBalance()) + transactions.get(i).getCurrency() + "\t\t" + transactions.get(i).getTitle() +"\t\t" + transactions.get(i).getFirstAccName() );
            }

        }
        listView.setOnMouseClicked(event -> {
            if(event.getClickCount()==2)
            {
                try {
                    switchToProperties(transactions.get(listView.getSelectionModel().getSelectedIndex()));
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void switchToProperties(transaction transaction) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionProperties.fxml"));
        root=loader.load();
        transactionPropertiesController transactionPropertiesController = loader.getController();
        transactionPropertiesController.init(transaction);
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
