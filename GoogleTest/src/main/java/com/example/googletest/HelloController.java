package com.example.googletest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        javafx.application.Application.launch(HelloApplication.class, args);
    }
}