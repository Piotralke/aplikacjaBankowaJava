module com.example.aplikacjabankowajava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.money;


    opens com.example.aplikacjabankowajava to javafx.fxml;
    exports com.example.aplikacjabankowajava;
}