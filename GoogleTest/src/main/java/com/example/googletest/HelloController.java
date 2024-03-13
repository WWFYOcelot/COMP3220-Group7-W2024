/**
 * @author Faraan Rashid
 * Controller class for the Windsor Biking Data JavFX application
 */

package com.example.googletest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    /**
     * Event handler method for the "Hello" button click.
     * Updates the welcome text label.
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        javafx.application.Application.launch(HelloApplication.class, args);
    }
}
