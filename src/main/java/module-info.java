module com.example.aplikacjabankowajava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.money;
    requires org.jetbrains.annotations;
    requires org.testng;


    opens com.example.aplikacjabankowajava to javafx.fxml;
    exports com.example.aplikacjabankowajava;
}