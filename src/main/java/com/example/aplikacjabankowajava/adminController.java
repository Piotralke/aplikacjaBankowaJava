package com.example.aplikacjabankowajava;

import http.TableA;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.tables.ArrayOfExchangeRatesTable;

import java.io.IOException;

public class adminController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelLogowania.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void show()
    {
        try {
            ArrayOfExchangeRatesTable arrayERT = new TableA().currentTable();

            for (int i = 0; i < arrayERT.getExchangeRatesTables().size(); i++) {
                System.out.println(arrayERT.getExchangeRatesTables().get(i).getTable());
                System.out.println(arrayERT.getExchangeRatesTables().get(i).getNo());
                System.out.println(arrayERT.getExchangeRatesTables().get(i).getEffectiveDate());
                for (int j = 0; j < arrayERT.getExchangeRatesTables().get(i).getRates().size(); j++) {
                    System.out.println(
                            // applies to archive exchange rates, it could return null value
                            arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCountry() + " - " +
                                    // applies to archive exchange rates, it could return null value
                                    arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getSymbol() + " - " +
                                    arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCurrency() + " - " +
                                    arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode() + " - " +
                                    arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getMid());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToList(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userList.fxml"));
        root=loader.load();
        listController listController = loader.getController();
        Node n = (Node)event.getSource();
        String id = n.getId();
        serialization.serializeString("button.txt",id);
        //System.out.println(id);
        listController.initList();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAdd(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addUser.fxml"));
        root=loader.load();
        addController addController = loader.getController();
        addController.init(serialization.deserializeManager("manager.txt"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
