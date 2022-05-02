package com.example.aplikacjabankowajava;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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
    private Button back;

    @FXML
    public void initList() throws IOException, ClassNotFoundException {
        String idB=serialization.deserializeString("button.txt"); //tu mamy fx:id przycisku ktory wywolal nasza akcje wiec po tym mozemy dostowac rzeczy wyswietlane w listView :)

        ArrayList<user> userList = serialization.deserializeUserList("data.txt");

        for(int i=0;i<userList.size();i++)
        {
            if(!serialization.deserializeManager("manager.txt")){
                if(!userList.get(i).isAdminAccess())
                {
                    listView.getItems().add(userList.get(i).getLogin() + "\t\t\t\t\t\t" + userList.get(i).getName() +" " + userList.get(i).getSurname() );
                    //   listView.getItems().add(userList.get(i).toString());
                }
            }else{
                listView.getItems().add(userList.get(i).getLogin() + "\t\t\t\t\t\t" + userList.get(i).getName() +" " + userList.get(i).getSurname() );
                //   listView.getItems().add(userList.get(i).toString());
            }

            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getClickCount()==2)
                    {

                        String login = (listView.getSelectionModel().getSelectedItem().toString());
                        String loginT = String.valueOf(login.substring(0, 8));
                        try {
                            if(idB.equals("historyButton")) {
                                switchToTransactions(loginT);
                            }else if(idB.equals("changeButton")){
                                switchToChanges(loginT);
                            }else if(idB.equals("creditButton")){

                            }else if(idB.equals("depositButton")){

                            }
                        }catch (IOException e) {
                            throw new RuntimeException(e);
                        }catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            });
        }
        userList = new ArrayList<>();
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
}
