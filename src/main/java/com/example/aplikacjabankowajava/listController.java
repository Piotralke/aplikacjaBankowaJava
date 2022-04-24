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
        ArrayList<user> userList = serialization.deserializeList("data.txt");
        for(int i=0;i<userList.size();i++)
        {
            if(!userList.get(i).isAdminAccess())
            {
                listView.getItems().add(userList.get(i).getLogin() + "\t\t\t\t\t\t" + userList.get(i).getName() +" " + userList.get(i).getSurname() );
             //   listView.getItems().add(userList.get(i).toString());
            }
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
    }



    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        switchToScene1(event);
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
