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

public class transactionController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ListView listView;

    @FXML
    private Button back;

    @FXML
    public void initList() throws IOException, ClassNotFoundException {
        String login = serialization.deserializeString("login.txt");
        ArrayList<user> userList = serialization.deserializeUserList("data.txt");
        int j;
        for(j = 0;j<userList.size()-1;j++)
        {
            if(userList.get(j).getLogin().equals(login))
            {
                break;
            }
        }
        ArrayList<transaction> transactions = userList.get(j).getTransacionList();
        for(int i=0;i<transactions.size();i++)
        {
            listView.getItems().add(transactions.get(i).getBalance() + "\t\t\t\t" + transactions.get(i).getTitle() +"\t\t" + transactions.get(i).getSecondAccName() );
        }
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2)
                {
                    try {
                        switchToProperties(listView.getSelectionModel().getSelectedIndex());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        userList = new ArrayList<>();
        transactions = new ArrayList<>();
    }
    public void switchToProperties(int numberT) throws IOException, ClassNotFoundException {
        String login = serialization.deserializeString("login.txt");
        ArrayList<user> userList = serialization.deserializeUserList("data.txt");
        int j;
        for(j = 0;j<userList.size()-1;j++)
        {
            if(userList.get(j).getLogin().equals(login))
            {
                break;
            }
        }
        ArrayList<transaction> transactions = userList.get(j).getTransacionList();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionProperties.fxml"));
        root=loader.load();
        transactionPropertiesController transactionPropertiesController = loader.getController();
        transactionPropertiesController.init(transactions.get(numberT));
        stage = (Stage)listView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        userList = new ArrayList<>();
        transactions = new ArrayList<>();
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
