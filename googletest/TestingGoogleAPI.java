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
import java.util.List;

public class TestingGoogleAPI {

    public static void test() throws IOException, InterruptedException, ApiException {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBn5POt-ThaW903dR_6OuMuy2uJkH-nQfc");

        // Path to the JSON file
        String filePath = "src/main/java/com/example/googletest/PotholeData.json";
        String potholeAddress = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read JSON data from file
            PotholeServiceRequest serviceRequest = new Gson().fromJson(reader, PotholeServiceRequest.class);
            potholeAddress = serviceRequest.street;
            System.out.println(potholeAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GeocodingResult[] response =  GeocodingApi.geocode(context,
                potholeAddress + ", Windsor, Ontario").await();
        Gson gson = new GsonBuilder().create();
        String jsonOutput = gson.toJson(response);

        GeocodingParser.Result[] results = gson.fromJson(jsonOutput, GeocodingParser.Result[].class);

        System.out.println(results[0].geometry.location.toString());
    }
    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        test();
    }
    // Hooray!
}
