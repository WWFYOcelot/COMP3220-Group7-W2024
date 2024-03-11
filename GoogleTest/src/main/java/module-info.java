module com.example.googletest {
    requires google.maps.services;
    requires java.sql;
    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.fxml;

    opens com.example.googletest to javafx.fxml;
    exports com.example.googletest;
}