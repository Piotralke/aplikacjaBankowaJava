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

public class listController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView listView;

    @FXML
    public void initList() throws IOException, ClassNotFoundException {
        String idB=serialization.deserializeString("button.txt"); //tu mamy fx:id przycisku ktory wywolal nasza akcje wiec po tym mozemy dostowac rzeczy wyswietlane w listView :)

        ArrayList<user> userList = serialization.deserializeUserList("data.txt");
        listView.getItems().clear();
        for(int i=0;i<userList.size();i++)
        {

                if((idB.equals("changeButton") || idB.equals("deleteButton"))&& serialization.deserializeManager("manager.txt")) {
                    listView.getItems().add(userList.get(i).getLogin() + "\t\t\t\t\t\t" + userList.get(i).getName() +" " + userList.get(i).getSurname() );
                }else if(idB.equals("creditButton") && !userList.get(i).isAdminAccess()) {
                    if(!userList.get(i).getCreditList().isEmpty()){
                        if(userList.get(i).getCreditList().get(0).getStatus().equals("Oczekujący")){
                            listView.getItems().add(userList.get(i).getLogin() +"\t\t\t\t\t\t" + userList.get(i).getCreditList().get(0).getNumber());
                        }
                    }
                } else if(!userList.get(i).isAdminAccess()){
                    listView.getItems().add(userList.get(i).getLogin() + "\t\t\t\t\t\t" + userList.get(i).getName() +" " + userList.get(i).getSurname() );
                }
            }

            listView.setOnMouseClicked(event -> {
                if(event.getClickCount()==2)
                {
                    String login = (listView.getSelectionModel().getSelectedItem().toString());
                    String loginT = login.substring(0, 8);
                    try {
                        switch (idB) {
                            case "historyButton":
                                switchToTransactions(loginT);
                                break;
                            case "changeButton":
                                switchToChanges(loginT);
                                break;
                            case "creditButton":
                                switchToCreditAccept(loginT);
                                break;
                            case "depositButton":
                                switchToTransfer(loginT);
                                break;
                            case"deleteButton":
                                deleteUser(loginT);
                                break;
                        }
                    }catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToTransactions(String login) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionList.fxml"));
        root=loader.load();
        serialization.serializeString("login.txt",login);
        transactionController transactionController = loader.getController();
        transactionController.initList();
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToChanges(String login) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeUser.fxml"));
        root=loader.load();
        changeController changeController = loader.getController();
        changeController.init(login);
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToCreditAccept(String login) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creditProperties.fxml"));
        root=loader.load();
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        int i;
        for(i=0;i<tempList.size();i++){
            if(tempList.get(i).getLogin().equals(Long.valueOf(login)))
                break;
        }
        creditPropertiesController creditPropertiesController = loader.getController();
        creditPropertiesController.init(tempList.get(i).getCreditList().get(0),true);
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToTransfer(String login) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addTransfer.fxml"));
        root=loader.load();
        adminTransferController adminTransferController = loader.getController();
        adminTransferController.init();
        serialization.serializeString("login.txt",login);
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected void deleteUser(String login) throws IOException, ClassNotFoundException {
        ArrayList<user> tempList = serialization.deserializeUserList("data.txt");
        int i;
        for(i=0;i<tempList.size();i++){
            if(tempList.get(i).getLogin().equals(Long.valueOf(login)))
                break;
        }
        tempList.remove(i);
        serialization.serializeUserList("data.txt",tempList);
        initList();
    }
}
