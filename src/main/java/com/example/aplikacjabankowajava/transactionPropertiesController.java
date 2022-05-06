package com.example.aplikacjabankowajava;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class transactionPropertiesController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label transactionNumberText;
    @FXML
    private Label titleText;
    @FXML
    private Label amountText;
    @FXML
    private  Label dateText;
    @FXML
    private Label senderName;
    @FXML
    private Label receiverName;
    @FXML
    private  Label senderAcc;
    @FXML
    private  Label receiverAcc;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label typeLabel;
    private transaction tempT;

    public void init(transaction transaction){
        tempT=transaction;
        transactionNumberText.setText("Transakcja nr. "+transaction.getNumber());
        titleText.setText("Tytuł transakcji:\n"+transaction.getTitle());
        dateText.setText("Data operacji:\n"+transaction.getDate());
        senderName.setText("Nadawca:\n"+transaction.getFirstAccName());
        receiverName.setText("Odbiorca:\n"+transaction.getSecondAccName());
        balanceLabel.setText("Saldo po operacji:\n"+String.format("%.02f",transaction.getNewBalance()) + transaction.getCurrency());
        if (transaction.isTransactionType()) {
            amountText.setText("Kwota operacji:\n"+ "-" + String.format("%.02f", transaction.getBalance())+ transaction.getCurrency());
            typeLabel.setText("Typ transakcji:\nWychodząca");
        } else  {
            amountText.setText("Kwota operacji:\n"+ "+" + String.format("%.02f", transaction.getBalance())+ transaction.getCurrency());
            typeLabel.setText("Typ transakcji:\nPrzychodząca");
        }
        if(transaction.getFirstAcc()==null)
        {
            senderAcc.setText("");
        }
        else
        {
            senderAcc.setText("Nr konta nadawcy\n"+transaction.getFirstAcc().toString());
        }
        if(transaction.getSecondAcc()==null)
        {
            receiverAcc.setText("");
        }
        else
        {
            receiverAcc.setText("Nr konta odbiorcy\n"+transaction.getSecondAcc().toString());
        }
    }

    @FXML
    protected void generate() throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage();
        document.addPage(firstPage);
        PDRectangle rect = firstPage.getMediaBox();
        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);

        PDType0Font font = PDType0Font.load(document, new File("src/main/resources/arial.ttf"));
        PDType0Font fontBold = PDType0Font.load(document, new File("src/main/resources/arialbd.ttf"));

        int line = 0;
        PDImageXObject pdfImage = PDImageXObject.createFromFile("src/main/resources/logo.png", document);
        contentStream.drawImage(pdfImage, 0,  rect.getHeight()- 100*(++line), 100,100);



        contentStream.beginText();
        contentStream.setFont(fontBold, 30);
        contentStream.newLineAtOffset((float) (rect.getWidth()*0.25), rect.getHeight() -  25*(++line));
        contentStream.showText("Potwierdzenie przelewu:");
        contentStream.endText();


        contentStream.beginText();
        contentStream.setFont(fontBold, 30);
        contentStream.newLineAtOffset((float) (rect.getWidth()*0.25), rect.getHeight() -  25*(++line));
        contentStream.showText(tempT.getNumber());
        contentStream.endText();

        line+=8;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset( 100, rect.getHeight() -  15*(++line));
        contentStream.showText("Nadawca:  " + tempT.getFirstAccName());
        contentStream.endText();
        line+=2;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset(100, rect.getHeight() -  15*(++line));
        contentStream.showText("Numer konta nadawcy:  " + tempT.getFirstAcc());
        contentStream.endText();

        line+=6;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset(100, rect.getHeight() -  15*(++line));
        contentStream.showText("Odbiorca:  " + tempT.getSecondAccName());
        contentStream.endText();

        line+=2;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset(100, rect.getHeight() -  15*(++line));
        contentStream.showText("Numer konta odbiorcy:  " + tempT.getSecondAcc());
        contentStream.endText();

        line+=2;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset((float) (rect.getWidth()*0.25), rect.getHeight() -  25*(++line));
        contentStream.showText("Tytuł przelewu:  " + tempT.getTitle());
        contentStream.endText();
        line+=2;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset((float) (rect.getWidth()*0.25), rect.getHeight() -  25*(++line));
        contentStream.showText("Data:  " + tempT.getDate());
        contentStream.endText();
        line+=2;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset((float) (rect.getWidth()*0.25), rect.getHeight() -  25*(++line));
        contentStream.showText("Kwota:  " + tempT.getAmount()+tempT.getCurrency());
        contentStream.endText();
        line+=2;
        contentStream.beginText();
        contentStream.setFont(font, 20);
        contentStream.newLineAtOffset((float) (rect.getWidth()*0.25), rect.getHeight() -  25*(++line));
        if(tempT.isTransactionType()){
            contentStream.showText("Typ operacji:  Przelew wychodzący");
        }else{
            contentStream.showText("Typ operacji:  Przelew przychodzący");
        }
        contentStream.endText();
        contentStream.close();

        document.save("src/pobrane/"+tempT.getNumber()+".pdf");
        document.close();
    }

    @FXML
    protected void goBack(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader loader;
        if(serialization.deserializeString("exit.txt").equals("user")){
            loader = new FXMLLoader(getClass().getResource("panelUser.fxml"));
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
        }else{
            loader = new FXMLLoader(getClass().getResource("transactionList.fxml"));
            root=loader.load();
            transactionController transactionController = loader.getController();
            transactionController.initList();
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
