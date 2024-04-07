/**
 * @author Faraan Rashid
 * @version 1.0
 *
 * The GeocodingParser class provides structures for parsing geocoding results from Google Maps API, whose data values
 *  are then accessible through its methods.
 *
 * Note that while this class should use proper encapsulation, ie. private fields and getters and setters, the JSON parser we used wasn't configured to handle this.
 *  As we considered reconfiguring the JSON parser to be outside the scope of this iteration, we opted to postpone this change to a future iteration.
 * 
 */
package com.example.finaltest2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;
import java.util.List;

public class GeocodingParser {

    /**
     * Represents the results of a geocoding request.
     */
    public static class Results {
        public List<Result> results;
    }

    /**
     * Represents a single geocoding result.
     */
    public static class Result {
        @SerializedName("address_components")
        public List<ResultAddressComponent> resultAddressComponents;
        public Geometry geometry;
        @SerializedName("place_id")
        public String placeId;
        @SerializedName("plus_code")
        public PlusCode plusCode;
        public String status;
    }

    /**
     * Represents an address component of a geocoding result.
     */
    public static class ResultAddressComponent {
        @SerializedName("long_name")
        public String longName;
        @SerializedName("short_name")
        public String shortName;
        public List<String> types;

        /**
         * Returns a string representation of the address component.
         *
         * @return A string containing the long name, short name, and types of the address component.
         */
        @Override
        public String toString() {
            return "Long Name: " + longName + ", Short Name: " + shortName + ", Types: " + types;
        }
    }

    /**
     * Represents the geometry information of a geocoding result.
     */
    public static class Geometry {
        public Location location;
        @SerializedName("location_type")
        public String locationType;
        public Viewport viewport;

        /**
         * Returns a string representation of the geometry.
         *
         * @return A string containing the location, location type, and viewport of the geometry.
         */
        @Override
        public String toString() {
            return "Geometry{" +
                    "location=" + location +
                    ", locationType='" + locationType + '\'' +
                    ", viewport=" + viewport +
                    '}';
        }
    }

    /**
     * Represents a geographical location.
     */
    public static class Location {
        public double lat;
        public double lng;

        /**
         * Returns a string representation of the location.
         *
         * @return A string containing the latitude and longitude of the location.
         */
        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }

    /**
     * Represents a viewport of a geographical area.
     */
    public static class Viewport {
        public Location northeast;
        public Location southwest;

        /**
         * Returns a string representation of the viewport.
         *
         * @return A string containing the northeast and southwest locations of the viewport.
         */
        @Override
        public String toString() {
            return "Viewport{" +
                    "northeast=" + northeast +
                    ", southwest=" + southwest +
                    '}';
        }
    }

    /**
     * Represents the Plus Code of a geocoding result.
     */
    public static class PlusCode {
        @SerializedName("compound_code")
        public String compoundCode;
        @SerializedName("global_code")
        public String globalCode;
        public List<String> types;

        /**
         * Returns a string representation of the Plus Code.
         *
         * @return A string containing the compound code, global code, and types of the Plus Code.
         */
        @Override
        public String toString() {
            return "PlusCode{" +
                    "compoundCode='" + compoundCode + '\'' +
                    ", globalCode='" + globalCode + '\'' +
                    ", types=" + types +
                    '}';
        }
    }
    public static Result[] getResponse(GeoApiContext context, String location) throws IOException, InterruptedException, ApiException {
        GeocodingResult[] response = GeocodingApi.geocode(context, location).await();
        // Convert geocoding response to JSON format
        Gson gson = new GsonBuilder().create();
        String jsonOutput = gson.toJson(response);
        // Parse the JSON output into GeocodingParser.Result array
        Result[] results = gson.fromJson(jsonOutput, Result[].class);
        return results;
    }
}
