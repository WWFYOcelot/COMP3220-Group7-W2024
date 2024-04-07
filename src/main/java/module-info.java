module com.example.finaltest2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires google.maps.services;
    requires java.desktop;
    requires javafx.swing;
    requires com.google.gson;


    opens com.example.finaltest2 to javafx.fxml;
    exports com.example.finaltest2;
}