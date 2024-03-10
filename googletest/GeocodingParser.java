package com.example.googletest;

import com.google.gson.annotations.SerializedName;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.List;

public class GeocodingParser {

    public static void main() throws IOException, InterruptedException, ApiException {
        System.out.println("Hello i am goeocidng parser");
    }

    public static class Results {
        public List<Result> results;
    }

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

    public static class ResultAddressComponent {
        @SerializedName("long_name")
        public String longName;
        @SerializedName("short_name")
        public String shortName;
        public List<String> types;

        @Override
        public String toString() {
            return "Long Name: " + longName + ", Short Name: " + shortName + ", Types: " + types;
        }
    }

    public static class Geometry {
        public Location location;
        @SerializedName("location_type")
        public String locationType;
        public Viewport viewport;

        @Override
        public String toString() {
            return "Geometry{" +
                    "location=" + location +
                    ", locationType='" + locationType + '\'' +
                    ", viewport=" + viewport +
                    '}';
        }
    }

    public static class Location {
        public double lat;
        public double lng;

        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }

    public static class Viewport {
        public Location northeast;
        public Location southwest;

        @Override
        public String toString() {
            return "Viewport{" +
                    "northeast=" + northeast +
                    ", southwest=" + southwest +
                    '}';
        }
    }

    public static class PlusCode {
        @SerializedName("compound_code")
        public String compoundCode;
        @SerializedName("global_code")
        public String globalCode;
        public List<String> types;

        @Override
        public String toString() {
            return "PlusCode{" +
                    "compoundCode='" + compoundCode + '\'' +
                    ", globalCode='" + globalCode + '\'' +
                    ", types=" + types +
                    '}';
        }
    }
}
