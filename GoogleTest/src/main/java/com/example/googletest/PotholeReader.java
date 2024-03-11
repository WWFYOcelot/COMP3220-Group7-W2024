/**
 * @author Faraan Rashid
 * @version 1.0
 * The PotholeReader class reads pothole data from a JSON file and retrieves their locations using Google Maps API.
 *  This is all the functionality needed to deal with requests to view areas where potholes may cause a hazard
 */

package com.example.googletest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PotholeReader {

    /**
     * API key for accessing Google Maps services.
     */
    private final String apiKey = "AIzaSyBn5POt-ThaW903dR_6OuMuy2uJkH-nQfc";

    /**
     * GeoApiContext for making Google Maps API requests.
     */
    private final GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

    /**
     * Retrieves pothole data from a JSON file.
     *
     * @return A list of PotholeDataParser objects representing pothole data.
     */
    private static List<PotholeDataParser> getPotholes() {
        List<PotholeDataParser> potholes = null;

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/googletest/PotholeData.json"))) {
            Gson gson = new Gson();
            potholes = gson.fromJson(br, new TypeToken<List<PotholeDataParser>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return potholes;
    }

    /**
     * Retrieves the locations of potholes.
     *
     * @return A list of LatLng objects representing the locations of potholes.
     * @throws IOException    If an I/O error occurs.
     * @throws InterruptedException If the thread is interrupted during execution.
     * @throws ApiException If the API request fails.
     */
    public List<LatLng> getPotholeLocations() throws IOException, InterruptedException, ApiException {
        List<LatLng> locations = new ArrayList<>();
        List<PotholeDataParser> potholes = PotholeReader.getPotholes();
        for (PotholeDataParser p : potholes) {
            GeocodingParser.Result[] results = GeocodingParser.getResponse(context, p.street + "Windsor, Ontario");
            locations.add(new LatLng(results[0].geometry.location.lat, results[0].geometry.location.lng));
        }
        return locations;
    }
}
