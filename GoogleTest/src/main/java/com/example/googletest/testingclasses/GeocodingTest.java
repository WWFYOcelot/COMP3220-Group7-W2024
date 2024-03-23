/**
 * @author Faraan Rashid
 * @version 1.0
 *
 * This class demonstrates the process of geocoding using the Google Maps API.
 * It reads pothole data from a JSON file, extracts the street address, and then
 * retrieves the latitude and longitude coordinates for that address.
 */
package com.example.googletest.testingclasses;

import com.example.googletest.GeocodingParser;
import com.example.googletest.PotholeDataParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GeocodingTest {

    /** The API key for accessing the Google Maps API. */
    private static final String apiKey = "api-key";

    /** The GeoApiContext object used to access the Google Maps API. */
    private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

    /**
     * Performs geocoding to retrieve latitude and longitude coordinates for the pothole address.
     * @throws IOException If an I/O error occurs while reading the JSON file.
     * @throws InterruptedException If the current thread is interrupted while waiting for the API response.
     * @throws ApiException If the Google Maps API returns an error response.
     */
    public static void GeocodingTestMethod() throws IOException, InterruptedException, ApiException {
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
        GeocodingParser.Results results = gson.fromJson(jsonOutput, GeocodingParser.Results.class);

        // Print the latitude and longitude of the pothole location
        System.out.println(results.results.get(0).geometry.location.toString());
    }
}
