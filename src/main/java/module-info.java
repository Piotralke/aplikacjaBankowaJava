module com.example.aplikacjabankowajava {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.testng;
    requires exchange.rates.nbp;


    opens com.example.aplikacjabankowajava to javafx.fxml;
    exports com.example.aplikacjabankowajava;
}