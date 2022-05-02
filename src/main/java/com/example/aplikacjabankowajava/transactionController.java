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
    public void initList(String login) throws IOException, ClassNotFoundException {
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
                    String login = (listView.getSelectionModel().getSelectedItem().toString());
                    Long loginT = Long.valueOf(login.substring(0,8));
                }
            }
        });
        userList = new ArrayList<>();
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
