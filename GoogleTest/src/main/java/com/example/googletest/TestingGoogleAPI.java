/**
 *
 * Gets latitude and longitude values from addresses obtained from Windsor Open Data
 *
 * Uses PotholeServiceRequest and GeocodingParser classes to parse JSON objects to access their values
 *
 */
package com.example.googletest;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestingGoogleAPI {

    /**
     * Tests the Google Maps API for geocoding by retrieving latitude and longitude for pothole addresses.
     *
     * @throws IOException    If an I/O error occurs while reading the JSON file.
     * @throws InterruptedException    If the thread is interrupted while waiting for the geocoding response.
     * @throws ApiException    If an error occurs while executing the geocoding request.
     */
    public static void test() throws IOException, InterruptedException, ApiException {
        // Set up Google API context with API key
        GeoApiContext.Builder contextBuilder = new GeoApiContext.Builder().apiKey("AIzaSyBn5POt-ThaW903dR_6OuMuy2uJkH-nQfc");
        GeoApiContext context = contextBuilder.build();

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
        test(); // Execute the test method
    }
    // Hooray!
}
