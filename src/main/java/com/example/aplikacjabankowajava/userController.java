package com.example.aplikacjabankowajava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
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

    Dialog<String> dialog = new Dialog<>();


    @FXML
    public void initUser(user user, int position) throws IOException, ClassNotFoundException {
        dialog.setTitle("Zmiana hasła");
        dialog.setHeaderText("Wymagana zmiana hasła!");
        ButtonType loginButtonType = new ButtonType("Zatwierdź", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.setOnCloseRequest(null);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> password.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return password.getText();
            }
            return null;
        });

        if(!user.isActive()){
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(passwordN ->{
                ArrayList<user> tempList = null;
                try {
                    tempList = serialization.deserializeUserList("data.txt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                tempList.get(position).setPassword(passwordN);
                tempList.get(position).setActive(true);
                try {
                    serialization.serializeUserList("data.txt",tempList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tempList = new ArrayList<>();
            });
        }

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
    public void initList(user user){
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
