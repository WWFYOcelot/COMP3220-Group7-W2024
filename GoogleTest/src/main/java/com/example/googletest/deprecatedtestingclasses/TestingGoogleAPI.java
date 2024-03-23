/**
 *
 * Gets latitude and longitude values from addresses obtained from Windsor Open Data
 *
 * Uses PotholeServiceRequest and GeocodingParser classes to parse JSON objects to access their values
 *
 */
package com.example.googletest.deprecatedtestingclasses;

import com.example.googletest.GeocodingParser;
import com.example.googletest.PotholeDataParser;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodedWaypoint;
import com.google.maps.model.GeocodingResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TestingGoogleAPI extends Application {
    private static final String apiKey = "api-key";
    private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

    /**
     * Tests the Google Maps API for geocoding by retrieving latitude and longitude for pothole addresses.
     *
     */
    @Override
    public void start(Stage primaryStage) {
        // Your API key
        // Location to center the map on
        LatLng center = new LatLng(40.7128, -74.0060); // New York, NY

        // Zoom level of the map
        int zoom = 12;

        // Size of the map (width x height in pixels)
        int width = 600;
        int height = 400;

        try {
            Image mapImage = pathMapFunc();

            ImageView imageView = new ImageView(mapImage);

            // Create a StackPane and add the ImageView to it
            StackPane root = new StackPane();
            root.getChildren().add(imageView);

            // Create the scene
            Scene scene = new Scene(root, width, height);

            // Set the stage title and scene, then show the stage
            primaryStage.setTitle("Static Map Display");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Image directionsMap() throws IOException, InterruptedException, ApiException {
        StaticMapsRequest.Path pathMap = new StaticMapsRequest.Path();
        DirectionsApiRequest.Waypoint windsor = new DirectionsApiRequest.Waypoint("Windsor, Ontario");
        DirectionsApiRequest.Waypoint toronto = new DirectionsApiRequest.Waypoint("Toronto, Ontario");
        DirectionsApiRequest request = new DirectionsApiRequest(context).waypoints(windsor, toronto);
        DirectionsApiRequest response = DirectionsApi.getDirections(context, "401 sunset Ave, windsor, ontario", "601 gauthier dr., tecumseh, ontario");
        GeocodedWaypoint[] waypoints = response.await().geocodedWaypoints;
        for (GeocodedWaypoint wp : waypoints)
            System.out.println(wp);
        return null;
    }

    public static Image pathMapFunc() throws IOException, InterruptedException, ApiException {
        StaticMapsRequest.Path pathMap = new StaticMapsRequest.Path();
        pathMap.addPoint("2285 Wyandotte Street West, Windsor, Ontario");
        pathMap.addPoint("1675 Wyandotte Street West, Windsor, Ontario");
        StaticMapsRequest newMap = StaticMapsApi.newRequest(context, new Size(400, 600)).zoom(14).path(pathMap);

        byte[] imageData = newMap.await().imageData;

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    public static void test() throws IOException, InterruptedException, ApiException {

    }

    public static void GeoCodingTest() throws IOException, InterruptedException, ApiException {
        // Set up Google API context with API key
        // Path to the JSON file containing pothole data
        String filePath = "src/main/java/com/example/googletest/PotholeData.json";
        String potholeAddress = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read JSON data from file and parse it into a PotholeServiceRequest object
            PotholeDataParser serviceRequest = new Gson().fromJson(reader, PotholeDataParser.class);
            potholeAddress = serviceRequest.street; // Extract street address from the service request
            System.out.println(potholeAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Perform geocoding to retrieve latitude and longitude for the pothole address
        GeocodingResult[] response =  GeocodingApi.geocode(context,
                potholeAddress + ", Windsor, Ontario").await();

        // Convert geocoding response to JSON format
        Gson gson = new GsonBuilder().create();
        String jsonOutput = gson.toJson(response);

        // Parse the JSON output into GeocodingParser.Result array
        GeocodingParser.Result[] results = gson.fromJson(jsonOutput, GeocodingParser.Result[].class);

        // Print the latitude and longitude of the pothole location
        System.out.println(results[0].geometry.location.toString());
    }

    /**
     * Main method to execute the test method.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException    If an I/O error occurs while reading the JSON file.
     * @throws InterruptedException    If the thread is interrupted while waiting for the geocoding response.
     * @throws ApiException    If an error occurs while executing the geocoding request.
     */
    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        //test(); // Execute the test method
        directionsMap();
    }
    // Hooray!
}
