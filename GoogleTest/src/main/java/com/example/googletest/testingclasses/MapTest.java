/**
 * @author Faraan Rashid
 * @version 1.0
 *
 * The MapTest class displays static maps with markers or paths using JavaFX.
 */

package com.example.googletest.testingclasses;

import com.example.googletest.CustomMapImage;
import com.example.googletest.GeocodingParser;
import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MapTest extends Application {

    /** The API key for accessing the Google Maps API. */
    private static final String apiKey = "api-key";
    private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
    private Image mapImage = null;

    /**
     * Generates a static map image with pothole markers.
     *
     * @param width The width of the image.
     * @param height The height of the image.
     * @param zoom The zoom level of the map.
     * @return The Image object representing the static map with pothole markers.
     * @throws IOException If an I/O error occurs while reading the map image.
     * @throws InterruptedException If the current thread is interrupted while waiting for the API response.
     * @throws ApiException If the Google Maps API returns an error response.
     */
    public Image mapWithPotholeMarkers(int width, int height, int zoom) throws IOException, InterruptedException, ApiException {
        GeocodingParser.Result[] results = GeocodingParser.getResponse(context, "Windsor, Ontario");
        LatLng windsor = new LatLng(results[0].geometry.location.lat, results[0].geometry.location.lng);

        StaticMapsRequest.Markers markers = new StaticMapsRequest.Markers();
        markers.addLocation(windsor);
        StaticMapsRequest staticMapRequest = StaticMapsApi.newRequest(context, new Size(width, height)).center(windsor).zoom(zoom).markers(markers);
        byte[] imageData = staticMapRequest.await().imageData;

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    /**
     * Generates a static map image with a path.
     *
     * @param width The width of the image.
     * @param height The height of the image.
     * @param zoom The zoom level of the map.
     * @return The Image object representing the static map with a path.
     * @throws IOException If an I/O error occurs while reading the map image.
     * @throws InterruptedException If the current thread is interrupted while waiting for the API response.
     * @throws ApiException If the Google Maps API returns an error response.
     */
    public Image mapWithPath(int width, int height, int zoom) throws IOException, InterruptedException, ApiException {
        StaticMapsRequest.Path pathMap = new StaticMapsRequest.Path();
        pathMap.addPoint("University of Windsor");
        pathMap.addPoint("Tecumseh Mall");
        StaticMapsRequest newMap = StaticMapsApi.newRequest(context, new Size(width, height)).zoom(zoom).path(pathMap);

        byte[] imageData = newMap.await().imageData;

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    /**
     * Entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     * @throws Exception If an exception occurs during the JavaFX application execution.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Button mapWithPotholes = new Button("Pothole Map");
        Button mapWithPath = new Button("Path Map");
        mapImage = new CustomMapImage().mapOfWindsor(600, 400, 12);
        ImageView imageView = new ImageView(mapImage);

        FlowPane buttons = new FlowPane(mapWithPotholes, mapWithPath);
        buttons.setHgap(10);

        mapWithPotholes.setOnAction(event -> {
            try {
                mapImage = mapWithPotholeMarkers(600, 400, 12);
                imageView.setImage(mapImage);
            } catch (IOException | InterruptedException | ApiException e) {
                e.printStackTrace();
            }
        });

        mapWithPath.setOnAction(event -> {
            try {
                mapImage = mapWithPath(600, 400, 12);
                imageView.setImage(mapImage);
            } catch (IOException | InterruptedException | ApiException e) {
                e.printStackTrace();
            }
        });

        // Create a VBox and add the ImageView and buttons to it
        VBox root = new VBox();
        root.getChildren().add(imageView);
        root.getChildren().add(buttons);

        // Create the scene
        Scene scene = new Scene(root, 800, 600);

        // Set the stage title and scene, then show the stage
        stage.setTitle("Static Map Display");
        stage.setScene(scene);
        stage.show();
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
