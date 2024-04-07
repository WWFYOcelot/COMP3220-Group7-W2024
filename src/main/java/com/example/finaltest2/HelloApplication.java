/**
 * @author Rushikesh Patel
 * @author Faraan Rashid
 *
 * JavaFX front-end for the Windsor Biking Data website/application
 */

package com.example.finaltest2;

import com.google.maps.errors.ApiException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HelloApplication class represents the main JavaFX application.
 */
public class HelloApplication extends Application {

    // Local variables to store the current map and message
    private Image currentMapImage = null;
    private final String currentMessage = "Coming Soon!.";

    /**
     * The entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Frontend Page");

        // Heading
        Label heading = new Label("Windsor Biking Data");
        heading.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #fff;");

        // Navbar items with enhanced styling
        Button item1 = createStyledButton("Local Bike Paths");
        Button item2 = createStyledButton("Routes");
        Button item3 = createStyledButton("Statistics");
        Button item4 = createStyledButton("Hazardous Areas");
        Button item5 = createStyledButton("Optimal");
        Button item6 = createStyledButton("Pleasures");
        Button item7 = createStyledButton("Exercise");
        Button resetButton = createStyledButton("Reset Image");

        CheckBox checkBox = new CheckBox("Public Transit");
        checkBox.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-text-fill: #fff;"); // Increase font size and padding

        // CustomMapImage instance
        CustomMapImage customMapImage = new CustomMapImage();

        // Display map of Windsor by default
        currentMapImage = null;
        try {
            currentMapImage = customMapImage.mapOfWindsor(800, 600, 12);
        } catch (IOException | InterruptedException | ApiException e) {
            e.printStackTrace();
        }
        ImageView mapView = new ImageView(currentMapImage);

        // Hazardous Areas button event handler
        item4.setOnAction(event -> {
            try {
                Image hazardousAreasMapImage = customMapImage.mapWithPotholeMarkers(800, 600, 12);
                mapView.setImage(hazardousAreasMapImage);
            } catch (IOException | InterruptedException | ApiException e) {
                e.printStackTrace();
            }
        });

        resetButton.setOnAction(e -> {
            // Reset the image to its initial state
            try {
                mapView.setImage(customMapImage.mapOfWindsor(800, 600, 12));
            } catch (IOException | InterruptedException | ApiException ex) {
                ex.printStackTrace();
            }
        });

        // Navbar layout
        FlowPane navbar = new FlowPane(item1, item2, item3, item4, item5, item6, item7, checkBox);
        navbar.setHgap(10);
        navbar.setStyle("-fx-padding: 10px; -fx-background-color: #2196F3; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        VBox topPart = new VBox(heading, navbar);
        VBox mapBox = new VBox(10, mapView, resetButton);
        // Root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #64B5F6, #81C784, #FFD54F); -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        root.setTop(topPart);
        root.setLeft(mapBox);

        // Event handler for buttons other than "Hazardous Areas"
        EventHandler<ActionEvent> nonHazardousAreasButtonHandler = event -> {
            // Display the message that the feature is not yet available
            try {
                mapView.setImage(customMapImage.mapOfWindsor(800, 600, 12)); // Clear the map
            } catch (IOException | InterruptedException | ApiException e) {
                e.printStackTrace();
            }
            root.setRight(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Action");
            alert.setHeaderText("Unavailable Feature");
            alert.setContentText("You have selected a feature that has not yet been developed. We are working on it!");
            alert.showAndWait();
            displayMessage(currentMessage);
        };

        // Set the event handlers for non-Hazardous Areas buttons
        item1.setOnAction(nonHazardousAreasButtonHandler);
        item3.setOnAction(nonHazardousAreasButtonHandler);
        item5.setOnAction(nonHazardousAreasButtonHandler);
        item6.setOnAction(nonHazardousAreasButtonHandler);
        item7.setOnAction(nonHazardousAreasButtonHandler);


        item2.setOnAction(event -> {
            // Display text boxes for start and end
            TextField startTextField = new TextField();
            TextField endTextField = new TextField();
            Button resetLocs = createStyledButton("Reset");
            Button takeInput = createStyledButton("Enter");
            FlowPane inputButtons = new FlowPane(takeInput, resetLocs);
            inputButtons.setHgap(10);
            VBox textBoxes = new VBox(new Label("Start:"), startTextField, new Label("End:"), endTextField, inputButtons);
            root.setRight(textBoxes);
            takeInput.setOnAction(event2 -> {
                try {
                    mapView.setImage(customMapImage.mapWithPath(startTextField.getText(), endTextField.getText(), 800, 600, 12));
                } catch (IOException | InterruptedException | ApiException e) {
                    e.printStackTrace();
                }
            });
            resetLocs.setOnAction(event2 -> {
                startTextField.setText("");
                endTextField.setText("");
                try {
                    mapView.setImage(customMapImage.mapOfWindsor(800, 600, 12));
                } catch (IOException | InterruptedException | ApiException e) {
                    e.printStackTrace();
                }
            });
        });


        // Create scene and set it in the stage
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    /**
     * Utility method to create a styled button.
     *
     * @param text The text to display on the button.
     * @return The styled button.
     */
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 5;");
        return button;
    }

    /**
     * Display a message on the map view.
     *
     * @param message The message to display.
     */
    private void displayMessage(String message) {
        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #000;");

        // Clear the current map
        currentMapImage = null;

        // Create a VBox to hold the message label
        VBox messageBox = new VBox(messageLabel);

        // Set the VBox
    }
}