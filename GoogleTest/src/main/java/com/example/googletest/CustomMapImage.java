/**
 * @author Faraan Rashid
 * @version 1.0
 * CustomMapImage class generates static map images using Google Maps API. For our implemenation, we can generate a blank map of Windsor,
 *  or a map that shows markers for pothole service requests in Windsor
 * This class can have methods added to it in the future to cover more use cases
 */

package com.example.googletest;

import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.List;

public class CustomMapImage {

    /**
     * API key for accessing Google Maps services.
     */
    private final String apiKey = "AIzaSyBn5POt-ThaW903dR_6OuMuy2uJkH-nQfc";
    private final GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

    /**
     * Generates a static map image of Windsor, Ontario.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @param zoom   The zoom level of the map.
     * @return An Image object representing the static map image.
     * @throws IOException    If an I/O error occurs.
     * @throws InterruptedException If the thread is interrupted during execution.
     * @throws ApiException If the API request fails.
     */
    public Image mapOfWindsor(int width, int height, int zoom) throws IOException, InterruptedException, ApiException {
        GeocodingParser.Result[] results = GeocodingParser.getResponse(context, "Windsor, Ontario");

        LatLng windsor = new LatLng(results[0].geometry.location.lat, results[0].geometry.location.lng);

        // Windsor, Ontario coordinates
        StaticMapsRequest staticMapRequest = StaticMapsApi.newRequest(context, new Size(width, height)).center(windsor).zoom(zoom);
        byte[] imageData = staticMapRequest.await().imageData;

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);
        Image mapImage = SwingFXUtils.toFXImage(bufferedImage, null);

        return mapImage;
    }

    /**
     * Generates a static map image of Windsor, Ontario with pothole markers.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @param zoom   The zoom level of the map.
     * @return An Image object representing the static map image with pothole markers.
     * @throws IOException    If an I/O error occurs.
     * @throws InterruptedException If the thread is interrupted during execution.
     * @throws ApiException If the API request fails.
     */
    public Image mapWithPotholeMarkers(int width, int height, int zoom) throws IOException, InterruptedException, ApiException {
        GeocodingParser.Result[] results = GeocodingParser.getResponse(context, "Windsor, Ontario");
        LatLng windsor = new LatLng(results[0].geometry.location.lat, results[0].geometry.location.lng);

        StaticMapsRequest.Markers markers = new StaticMapsRequest.Markers();
        PotholeReader potholes = new PotholeReader();
        List<LatLng> locations = potholes.getPotholeLocations();

        for (LatLng l : locations) markers.addLocation(l);

        StaticMapsRequest staticMapRequest = StaticMapsApi.newRequest(context, new Size(width, height)).center(windsor).zoom(zoom).markers(markers);
        byte[] imageData = staticMapRequest.await().imageData;

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        BufferedImage bufferedImage = ImageIO.read(bis);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
