package com.example.aplikacjabankowajava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class userController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private ListView transactionList;
    @FXML
    private Label balanceLabel;

    @FXML
    private Label numerKonta;

    private Stage stage;
    private Scene scene;
    private Parent root;
    Popup popup = new Popup();
    Label label = new Label("Skopiowano do schowka!");



    @FXML
    public void initUser(user user){
        welcomeLabel.setText("Witaj " + user.getName());
        balanceLabel.setText(user.getBalance().toString() + user.getCurrency());
        numerKonta.setText("Numer konta: " + user.getAccNumber());
        popup.getContent().add(label);
        //label.setMinWidth();
        //label.setMinHeight();
        //label.setLayoutX(40);
        //label.setLayoutY(40);
    }

    @FXML
    public void toClipboard(){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        String temp = numerKonta.getText();
        content.putString(temp.substring(13));
        clipboard.setContent(content);
       // Label label = new Label("Skopiowano do schowka!");

       // popup.getContent().add(label);
        //label.setMinWidth(200);
        //label.setMinHeight(200);
        popup.show(transactionList.getScene().getWindow());

        Timer timerClipboardPopup = new Timer();
        timerClipboardPopup.schedule(new TimerTask() {
            @FXML
            @Override
            public void run() {
                Platform.runLater(()->{
                    popup.hide();
                    timerClipboardPopup.cancel();
                });
            }
        },5000);

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
