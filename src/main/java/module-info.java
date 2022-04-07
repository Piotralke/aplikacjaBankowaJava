module com.example.aplikacjabankowajava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aplikacjabankowajava to javafx.fxml;
    exports com.example.aplikacjabankowajava;
}