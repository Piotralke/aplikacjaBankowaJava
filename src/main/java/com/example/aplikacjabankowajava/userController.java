package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class userController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private ListView transactionList;
    @FXML
    private Label balanceLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initUser(user user){
        welcomeLabel.setText("Witaj " + user.getName());
        balanceLabel.setText(user.getBalance().toString() + user.getCurrency());
    }

    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelLogowania.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initList(user user) throws IOException, ClassNotFoundException {
        ArrayList<transaction> transactions = user.getTransacionList();
        for(int i=0;i<transactions.size();i++)
        {
            transactionList.getItems().add(transactions.get(i).getBalance() + "\t\t" + transactions.get(i).getTitle() +"\t\t" + transactions.get(i).getSecondAccName() );
        }
        transactionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2)
                {
                    String login = (transactionList.getSelectionModel().getSelectedItem().toString());
                    Long loginT = Long.valueOf(login.substring(0,8));
                }
            }
        });
    }

}
