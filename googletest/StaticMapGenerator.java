package com.example.googletest;

import com.google.maps.*;
import com.google.maps.model.LatLng;
import com.google.maps.StaticMapsApi;
import com.google.maps.model.*;
import com.google.maps.model.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class StaticMapGenerator {

    public static void main(String[] args) {
        // Windsor, Ontario coordinates
        LatLng location = new LatLng(42.3149, -83.0364);

        // Zoom level
        int zoom = 12;

        // Image size
        int width = 600;
        int height = 400;

        // Create a static map request
        StaticMapRequest staticMap = StaticMapsApi.newRequest()
                .size(width, height)
                .center(location)
                .zoom(zoom)
                .build();

        // Add a marker for Windsor
        StaticMapMarker marker = new StaticMapMarker.Builder()
                .location(location)
                .color("red")
                .label("W")
                .build();

        // Add the marker to the static map
        staticMap.markers(marker);

    }
}
