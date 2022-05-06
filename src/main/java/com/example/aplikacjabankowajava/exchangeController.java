package com.example.aplikacjabankowajava;

import http.TableA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.tables.ArrayOfExchangeRatesTable;

import java.io.IOException;

public class exchangeController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    protected ListView listView;
    @FXML
    protected Label exchangeText;

    public void init(String currency)
    {
        exchangeText.setText("Kurs waluty " + currency);

        try {
            listView.getItems().add("PLN\t\t" + String.format("%.02f", currencyConverter.convertCurrency(1.0f,currency,"PLN")));
            ArrayOfExchangeRatesTable arrayERT = new TableA().currentTable();

            for (int i = 0; i < arrayERT.getExchangeRatesTables().size(); i++) {

                for (int j = 0; j < arrayERT.getExchangeRatesTables().get(i).getRates().size(); j++) {
                    listView.getItems().add(arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode() + "\t\t" + String.format("%.02f", currencyConverter.convertCurrency(1.0f,currency,arrayERT.getExchangeRatesTables().get(i).getRates().get(j).getCode())));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("panelAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
