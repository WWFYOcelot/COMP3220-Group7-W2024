/**
 * @author Faraan Rashid
 * @version 1.0
 *
 * The StaticMapTest class displays a static map image using JavaFX.
 */

package com.example.googletest.testingclasses;

import com.example.googletest.CustomMapImage;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StaticMapTest extends Application {

    /**
     * Entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Location to center the map on
        LatLng center = new LatLng(40.7128, -74.0060); // New York, NY

        try {
            // Generate a static map image of Windsor
            Image mapImage = new CustomMapImage().mapOfWindsor(800, 600, 12);
            ImageView imageView = new ImageView(mapImage);

            // Create a StackPane and add the ImageView to it
            StackPane root = new StackPane();
            root.getChildren().add(imageView);

            // Create the scene
            Scene scene = new Scene(root, 800, 600);

            // Set the stage title and scene, then show the stage
            primaryStage.setTitle("Static Map Display");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method, launches the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
