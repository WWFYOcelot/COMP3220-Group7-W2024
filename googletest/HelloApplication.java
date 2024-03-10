package com.example.googletest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

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

        // Checkbox
        CheckBox checkBox = new CheckBox("Public Transit");
        checkBox.setStyle("-fx-font-size: 16px; -fx-padding: 5px; -fx-text-fill: #fff;"); // Increase font size and padding

        // Navbar layout
        HBox navbar = new HBox(item1, item2, item3, item4, item5, item6, item7, checkBox);
        navbar.setStyle("-fx-spacing: 10px; -fx-padding: 10px; -fx-background-color: #2196F3; " +
                "-fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        // Root layout
        VBox root = new VBox(heading, navbar);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #64B5F6, #81C784, #FFD54F); -fx-padding: 20px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        // Create scene and set it in the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 5;");
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}