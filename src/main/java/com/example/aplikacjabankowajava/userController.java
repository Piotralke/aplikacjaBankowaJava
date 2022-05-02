package com.example.aplikacjabankowajava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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

    public boolean isValid(String pass){
        boolean validation = true;
        if (pass.length() > 15 || pass.length() < 8)
        {
            System.out.println("Password must be less than 20 and more than 8 characters in length.");
            validation = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!pass.matches(upperCaseChars ))
        {
            System.out.println("Password must have atleast one uppercase character");
            validation = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!pass.matches(lowerCaseChars ))
        {
            System.out.println("Password must have atleast one lowercase character");
            validation = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!pass.matches(numbers ))
        {
            System.out.println("Password must have atleast one number");
            validation = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!pass.matches(specialChars ))
        {
            System.out.println("Password must have atleast one special character among @#$%");
            validation = false;
        }
        return validation;
    }

    @FXML
    public void initUser(user user, int position){
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
            if(isValid(newValue))
                loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.setOnCloseRequest(null);
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.getDialogPane().setContent(grid);
        Platform.runLater(password::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return password.getText();
            }
            return null;
        });

        if(!user.isActive()){
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(passwordN ->{
                ArrayList<user> tempList;
                try {
                    tempList = serialization.deserializeUserList("data.txt");
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                tempList.get(position).setPassword(passwordN);
                tempList.get(position).setActive(true);
                try {
                    serialization.serializeUserList("data.txt",tempList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        welcomeLabel.setText("Witaj " + user.getName());
        balanceLabel.setText(user.getBalance().toString() + user.getCurrency());
        numerKonta.setText("Numer konta: " + user.getAccNumber());
        //label.setMinWidth();
        //label.setMinHeight();
        //label.setLayoutX(40);
        //label.setLayoutY(40);
        popup.getContent().add(label);
    }

    @FXML
    public void initList(user user){
        ArrayList<transaction> transactions = user.getTransacionList();
        for(int i=0;i<transactions.size();i++)
        {
            transactionList.getItems().add(transactions.get(i).getBalance() + "\t\t" + transactions.get(i).getTitle() +"\t\t" + transactions.get(i).getSecondAccName() );
        }
        transactionList.setOnMouseClicked(event -> {
            if(event.getClickCount()==2)
            {
                try {
                    switchToProperties(transactions.get(transactionList.getSelectionModel().getSelectedIndex()));
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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

    public void switchToProperties(transaction transaction) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionProperties.fxml"));
        root=loader.load();
        transactionPropertiesController transactionPropertiesController = loader.getController();
        transactionPropertiesController.init(transaction);
        stage = (Stage)transactionList.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTransfer() throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newTransfer.fxml"));
        root=loader.load();
        transferController transferController = loader.getController();
        transferController.init();
        stage = (Stage)transactionList.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelLogowania.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
