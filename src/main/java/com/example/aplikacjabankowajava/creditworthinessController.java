package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class creditworthinessController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ChoiceBox periodChoice;
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
    @FXML
    private Label maxLabel;
    @FXML
    private Label creditLabel;

    public void init(){
        periodChoice.getItems().add("LAT");
        periodChoice.getItems().add("MSC");
    }

    @FXML
    protected void calculate(){

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
